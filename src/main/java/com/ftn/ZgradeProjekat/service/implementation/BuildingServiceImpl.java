package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.*;
import com.ftn.ZgradeProjekat.domain.DTO.BuildingDTO;
import com.ftn.ZgradeProjekat.domain.DTO.BuildingListItemDTO;
import com.ftn.ZgradeProjekat.domain.DTO.LocationDTO;
import com.ftn.ZgradeProjekat.repository.*;
import com.ftn.ZgradeProjekat.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djuro on 11/17/2017.
 */
@Service
public class BuildingServiceImpl implements BuildingService
{
    private final BuildingRepository buildingRepository;
    private final ApartmentRepository apartmentRepository;
    private final HallwayRepository hallwayRepository;
    private final BasementRepository basementRepository;
    private final AtticRepository atticRepository;

    @Autowired
    public BuildingServiceImpl(BuildingRepository buildingRepository,
                               ApartmentRepository apartmentRepository,
                               HallwayRepository hallwayRepository,
                               BasementRepository basementRepository,
                               AtticRepository atticRepository)
    {

        this.buildingRepository = buildingRepository;
        this.apartmentRepository = apartmentRepository;
        this.hallwayRepository = hallwayRepository;
        this.basementRepository = basementRepository;
        this.atticRepository = atticRepository;

    }

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

    @Override
    public BuildingDTO getById(Long buildingId)
    {
        Building building = buildingRepository.findById(buildingId);
        BuildingDTO buildingDTO = new BuildingDTO(building);
        return buildingDTO;
    }

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

    @Override
    public Boolean deleteBuilding(Long buildingId)
    {
        Building buildingForDelete = null;
        Boolean deleted = false;
        buildingForDelete = buildingRepository.findById(buildingId);
        if(buildingForDelete != null)
        {
            buildingRepository.delete(buildingForDelete);
            deleted = true;
        }

        return deleted;
    }

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
}
