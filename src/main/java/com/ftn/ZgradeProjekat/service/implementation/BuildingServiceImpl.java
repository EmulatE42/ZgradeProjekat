package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.*;
import com.ftn.ZgradeProjekat.domain.DTO.BuildingDTO;
import com.ftn.ZgradeProjekat.domain.DTO.BuildingListItemDTO;
import com.ftn.ZgradeProjekat.domain.DTO.LocationDTO;
import com.ftn.ZgradeProjekat.domain.DTO.ResponsiblePersonDTO;
import com.ftn.ZgradeProjekat.repository.*;
import com.ftn.ZgradeProjekat.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by djuro on 11/17/2017.
 */

/**
 * Servis za rad sa zgradama
 */
@Service
public class BuildingServiceImpl implements BuildingService
{
    private final BuildingRepository buildingRepository;
    private final ApartmentRepository apartmentRepository;
    private final HallwayRepository hallwayRepository;
    private final BasementRepository basementRepository;
    private final AtticRepository atticRepository;
    private final TenantRepository tenantRepository;
    private final ResponsiblePersonRepository responsiblePersonRepository;
    private final InstitutionRepository institutionRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public BuildingServiceImpl(BuildingRepository buildingRepository,
                               ApartmentRepository apartmentRepository,
                               HallwayRepository hallwayRepository,
                               BasementRepository basementRepository,
                               AtticRepository atticRepository,
                               TenantRepository tenantRepository,
                               ResponsiblePersonRepository responsiblePersonRepository,
                               InstitutionRepository institutionRepository,
                               LocationRepository locationRepository)
    {

        this.buildingRepository = buildingRepository;
        this.apartmentRepository = apartmentRepository;
        this.hallwayRepository = hallwayRepository;
        this.basementRepository = basementRepository;
        this.atticRepository = atticRepository;
        this.tenantRepository = tenantRepository;
        this.responsiblePersonRepository = responsiblePersonRepository;
        this.institutionRepository = institutionRepository;
        this.locationRepository = locationRepository;
    }

    /**
     *
     * @param buildingDTO DTO objekat zgrade
     * @return kreirana zgrada
     */
    @Override
    public Building addBuilding(BuildingDTO buildingDTO)
    {
        Building building = new Building(buildingDTO);
        Building saved = null;
        try
        {
            saved = buildingRepository.save(building);
        }
        catch(Exception e)
        {}
        return saved;
    }

    /**
     *
     * @param buildingId id zgrade koju zelimo dobaviti
     * @return DTO objekat pronadjene zgrade
     */
    @Override
    public BuildingDTO getById(Long buildingId)
    {
        Building building = buildingRepository.findById(buildingId);
        BuildingDTO buildingDTO = new BuildingDTO(building);
        return buildingDTO;
    }

    /**
     *
     * @return lista dto objekata svih zgrada
     */
    @Override
    public List<BuildingListItemDTO> getBuildings()
    {
        List<Building> buildings = buildingRepository.findAll();
        List<BuildingListItemDTO> buildingDTOs = new ArrayList<>();
        for(Building building : buildings)
        {
            buildingDTOs.add(new BuildingListItemDTO(building));
        }
        return buildingDTOs;
    }

    /**
     *
     * @param buildingId id zgrade za brisanje
     * @return da li je zgrada izbrisana
     */
    @Override
    public Boolean deleteBuilding(Long buildingId)
    {
        Building buildingForDelete = null;
        Boolean deleted = false;
        buildingForDelete = buildingRepository.findById(buildingId);
        if(buildingForDelete != null)
        {
            for(Location location : buildingForDelete.getLocations())
            {
                if(location instanceof Apartment)
                {
                    for(User user : ((Apartment) location).getOwners())
                    {
                        if(user instanceof Tenant)
                        {
                            ((Tenant) user).removeApartment((Apartment) location);
                        }
                    }
                    //((Tenant)((Apartment) location).getOwner()).setApartment(null);
                }
            }
            buildingRepository.delete(buildingForDelete);
            deleted = true;
        }

        return deleted;
    }

    /**
     *
     * @param locationDTO DTO objekat lokacije
     * @return DTO objekat kreirane lokacije
     */
    @Override
    public LocationDTO addLocationToBuilding(LocationDTO locationDTO)
    {
        Building building = buildingRepository.findById(locationDTO.getBuildingId());
        switch (locationDTO.getType()) {
            case "APARTMENT":
            {
                Apartment apartment = new Apartment();
                apartment.setBuilding(building);
                apartment.setFloor(locationDTO.getFloor());
                apartment.setSquare(locationDTO.getSquare());
                Apartment saved = apartmentRepository.save(apartment);
                building.addLocation(saved);
                locationDTO.setLocationId(saved.getId());
                break;
            }
            case "HALLWAY":
            {
                Hallway hallway = new Hallway();
                hallway.setBuilding(building);
                hallway.setNumberOfFloors(locationDTO.getNumberOfFloors());
                Hallway saved = hallwayRepository.save(hallway);
                building.addLocation(saved);
                locationDTO.setLocationId(saved.getId());
                break;
            }
            case "BASEMENT":
            {
                Basement basement = new Basement();
                basement.setBuilding(building);
                basement.setSquare(locationDTO.getSquare());
                Basement saved = basementRepository.save(basement);
                building.addLocation(saved);
                locationDTO.setLocationId(saved.getId());
                break;
            }
            case "ATTIC":
            {
                Attic attic = new Attic();
                attic.setBuilding(building);
                attic.setSquare(locationDTO.getSquare());
                attic.setFloor(locationDTO.getFloor());
                Attic saved = atticRepository.save(attic);
                building.addLocation(saved);
                locationDTO.setLocationId(saved.getId());
                break;
            }
            default:
                break;
        }
        return locationDTO;
    }

    /**
     *
     * @param responsiblePersonDTO DTO objekat odgovorne osobe
     * @param buildingId id zgrade u koju ubacujemo lokaciju
     * @return DTO objekat kreirane odgovorne osobe
     */
    @Override
    public ResponsiblePersonDTO addResponsiblePerson(ResponsiblePersonDTO responsiblePersonDTO, Long buildingId)
    {
        Tenant tenant = null;
        Institution institution = null;
        ResponsiblePerson responsiblePerson = null;
        if(responsiblePersonDTO.getIsTenant())
        {
            tenant = this.tenantRepository.findById(responsiblePersonDTO.getTenantDTO().getId());
            tenant.setIsResponsible(true);
            this.tenantRepository.save(tenant);
            responsiblePerson = new ResponsiblePerson(responsiblePersonDTO, tenant);
        }
        else {
            institution = this.institutionRepository.findOne(responsiblePersonDTO.getInstitutionDTO().getId());
            responsiblePerson = new ResponsiblePerson(responsiblePersonDTO, institution);
        }
        this.responsiblePersonRepository.save(responsiblePerson);
        Building building = this.buildingRepository.findById(buildingId);
        building.addResponsiblePerson(responsiblePerson);
        this.buildingRepository.save(building);
        responsiblePersonDTO.setId(responsiblePerson.getId());
        return responsiblePersonDTO;
    }

    /**
     *
     * @param id id zgrade ciju listu odgovornih lica zelimo da dobijemo
     * @return lista DTO objekata svih odgovornih lica iz zgrade
     */
    @Override
    public List<ResponsiblePersonDTO> getAllResponsiblePersons(Long id)
    {
        Set<ResponsiblePerson> responsiblePeople = this.buildingRepository.findById(id).getResponsiblePersons();
        List<ResponsiblePersonDTO> responsiblePersonDTOs = new ArrayList<>();
        for(ResponsiblePerson rp : responsiblePeople)
        {
            responsiblePersonDTOs.add(new ResponsiblePersonDTO(rp));
        }
        return responsiblePersonDTOs;
    }

    /**
     *
     * @param id id odgovorne osobe
     * @param buildingId id zgrade iz koje brisemo odgovorno lice
     * @return da li je odgovorno lice izbrisano
     */
    @Override
    public Boolean deleteResponsiblePerson(Long id, Long buildingId)
    {
        ResponsiblePerson deletedResponsiblePerson = null;
        Boolean deleted = false;
        deletedResponsiblePerson = this.responsiblePersonRepository.findOne(id);
        if(deletedResponsiblePerson!=null)
        {
            Building building = this.buildingRepository.findById(buildingId);
            building.removeResponsiblePerson(deletedResponsiblePerson);
            this.buildingRepository.save(building);
            this.responsiblePersonRepository.delete(deletedResponsiblePerson);
            deleted = true;
        }
        return deleted;
    }

    /**
     *
     * @param buildingId id zgrade u koju postavljamo predsednika
     * @param tenantId id starana kog postavljamo za predsednika
     * @return da li je stanar postavljen za predsednika
     */
    @Override
    public Boolean setBuildingManager(Long buildingId, Integer tenantId)
    {
        Building building = this.buildingRepository.findById(buildingId);
        Tenant tenant = null;
        Boolean saved = false;
        tenant = this.tenantRepository.findById(tenantId);
        if(tenant!= null) saved=true;
        tenant.setIsBuildingmManager(true);
        this.tenantRepository.save(tenant);
        if(building.getBuildingManager()!=null)
        {
            Tenant oldBuildingManager = (Tenant) building.getBuildingManager();
            oldBuildingManager.setIsBuildingmManager(false);
            this.tenantRepository.save(oldBuildingManager);
        }
        building.setBuildingManager(tenant);
        this.buildingRepository.save(building);
        return saved;
    }

    /**
     *
     * @param id id lokacije cija odgovorna lica dobavljamo
     * @return ista DTO objekata odgovornih lica
     */
    @Override
    public List<ResponsiblePersonDTO> getAllResponsiblePersonsByLocationId(Long id)
    {
        Location location = this.locationRepository.findById(id);
        Set<ResponsiblePerson> responsiblePeople = location.getBuilding().getResponsiblePersons();
        List<ResponsiblePersonDTO> responsiblePersonDTOs = new ArrayList<>();
        for(ResponsiblePerson rp : responsiblePeople)
        {
            responsiblePersonDTOs.add(new ResponsiblePersonDTO(rp));
        }
        return responsiblePersonDTOs;
    }

}
