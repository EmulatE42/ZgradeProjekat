package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.*;
import com.ftn.ZgradeProjekat.domain.DTO.LocationDTO;
import com.ftn.ZgradeProjekat.repository.LocationRepository;
import com.ftn.ZgradeProjekat.repository.TenantRepository;
import com.ftn.ZgradeProjekat.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by djuro on 11/19/2017.
 */
@Service
public class LocationServiceImpl implements LocationService
{
    private final LocationRepository locationRepository;
    private final TenantRepository tenantRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository,
                               TenantRepository tenantRepository)
    {
        this.locationRepository = locationRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public LocationDTO getLocationById(Long locationId)
    {
        Location location = locationRepository.findById(locationId);
        LocationDTO locationDTO = new LocationDTO(location);
        return locationDTO;
    }

    @Override
    public Boolean deleteLocation(Long locationId)
    {
        Location locationForDelete = null;
        Boolean deleted = false;
        locationForDelete = locationRepository.findById(locationId);
        if(locationForDelete != null)
        {
            locationRepository.delete(locationForDelete);
            deleted = true;
        }
        return deleted;
    }

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
