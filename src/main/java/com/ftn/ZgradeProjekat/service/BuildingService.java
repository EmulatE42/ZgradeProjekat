package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.Building;
import com.ftn.ZgradeProjekat.domain.DTO.BuildingDTO;
import com.ftn.ZgradeProjekat.domain.DTO.BuildingListItemDTO;
import com.ftn.ZgradeProjekat.domain.DTO.LocationDTO;

import java.util.List;

/**
 * Created by djuro on 11/17/2017.
 */
public interface BuildingService
{
    Building addBuilding(BuildingDTO building);

    BuildingDTO getById(Long buildingId);

    List<BuildingListItemDTO> getBuildings();

    Boolean deleteBuilding(Long buildingId);

    LocationDTO addLocationToBuilding(LocationDTO locationDTO);
}
