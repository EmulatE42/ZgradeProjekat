package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.*;
import com.ftn.ZgradeProjekat.domain.DTO.LocationDTO;
import com.ftn.ZgradeProjekat.repository.BuildingRepository;
import com.ftn.ZgradeProjekat.repository.LocationRepository;
import com.ftn.ZgradeProjekat.repository.TenantRepository;
import com.ftn.ZgradeProjekat.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by djuro on 11/19/2017.
 */

/**
 * Servis za rad sa lokacijama
 */
@Service
public class LocationServiceImpl implements LocationService
{
    private final LocationRepository locationRepository;
    private final TenantRepository tenantRepository;
    private final BuildingRepository buildingRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository,
                               TenantRepository tenantRepository,
                               BuildingRepository buildingRepository)
    {
        this.locationRepository = locationRepository;
        this.tenantRepository = tenantRepository;
        this.buildingRepository = buildingRepository;
    }

    /**
     *
     * @param locationId id lokacije koju zelimo
     * @return DTO objekat pronadjene lokacije
     */
    @Override
    public LocationDTO getLocationById(Long locationId)
    {
        Location location = locationRepository.findById(locationId);
        LocationDTO locationDTO = new LocationDTO(location);
        return locationDTO;
    }

    /**
     *
     * @param locationId id lokacije koju zelimo da izbrisemo
     * @return da li je lokacija izbrisana
     */
    @Override
    public Boolean deleteLocation(Long locationId)
    {
        Location locationForDelete = null;
        Boolean deleted = false;
        locationForDelete = locationRepository.findById(locationId);
        if(locationForDelete != null)
        {
            Building building = locationForDelete.getBuilding();
            building.removeLocation(locationForDelete);
            this.buildingRepository.save(building);
            if(locationForDelete instanceof  Apartment)
            {
                List<Tenant> tenants = this.tenantRepository.findAll();
                for(Tenant t : tenants)
                {
                    if(t.getApartments().contains(locationForDelete))
                    {
                        t.removeApartment((Apartment) locationForDelete);
                        this.tenantRepository.save(t);
                    }
                }
            }
            locationRepository.delete(locationForDelete);
            deleted = true;
        }
        return deleted;
    }

    /**
     *
     * @param apartmentId id apartmana kome dodajemo stanara
     * @param tenantId id stanara
     * @return da li je stanar povezan sa stanom
     */
    @Override
    public Boolean connectTenantAndApartment(Long apartmentId, Integer tenantId)
    {
        Apartment apartment = (Apartment) this.locationRepository.findById(apartmentId);
        Tenant tenant = this.tenantRepository.findById(tenantId);
        if(apartment == null || tenant == null) return false;
        apartment.addAddTenant(tenant);
        this.locationRepository.save(apartment);
        /*
        Building building = apartment.getBuilding();
        for(Location location : building.getLocations())
        {
            if(location instanceof Basement)
            {

            }
        }
        */
        tenant.addApartment(apartment);
        this.tenantRepository.save(tenant);
        return true;
    }
}
