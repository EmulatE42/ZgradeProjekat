package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.Building;
import com.ftn.ZgradeProjekat.domain.DTO.BuildingDTO;
import com.ftn.ZgradeProjekat.domain.DTO.BuildingListItemDTO;
import com.ftn.ZgradeProjekat.domain.DTO.LocationDTO;
import com.ftn.ZgradeProjekat.domain.DTO.ResponsiblePersonDTO;
import com.ftn.ZgradeProjekat.domain.Location;
import com.ftn.ZgradeProjekat.domain.ResponsiblePerson;
import com.ftn.ZgradeProjekat.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by djuro on 11/17/2017.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/building")
public class BuildingController
{
    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService)
    {
        this.buildingService = buildingService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Building> addBuilding(@RequestBody BuildingDTO building)
    {
        Building saved = buildingService.addBuilding(building);
        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    //building => BuildingDTO
    @RequestMapping(value = "/findByBuildingId/{buildingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuildingDTO> getById(@PathVariable("buildingId") Long buildingId)
    {
        BuildingDTO rest = this.buildingService.getById(buildingId);

        if(rest == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(rest, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllBuildings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BuildingListItemDTO>> getAllBuildings()
    {
        List<BuildingListItemDTO> buildingDTOs = buildingService.getBuildings();
        return new ResponseEntity<>(buildingDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteBuilding/{buildingId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteBuilding(@PathVariable("buildingId") Long buildingId)
    {
        Boolean deleted = buildingService.deleteBuilding(buildingId);
        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }


    @RequestMapping(value = "/addLocationToBuilding", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationDTO> addLocation(@RequestBody LocationDTO locationDTO)
    {
        LocationDTO savedLocationDTO = buildingService.addLocationToBuilding(locationDTO);
        return new ResponseEntity<>(savedLocationDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/addResponsiblePerson/{buildingId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponsiblePersonDTO> addResponsiblePerson(@PathVariable("buildingId") Long buildingId, @RequestBody ResponsiblePersonDTO responsiblePersonDTO)
    {
        ResponsiblePersonDTO savedResponsiblePerson = this.buildingService.addResponsiblePerson(responsiblePersonDTO, buildingId);
        if(savedResponsiblePerson == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(savedResponsiblePerson, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllResponsiblePersons/{buildingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponsiblePersonDTO>> getAllResponsiblePersons(@PathVariable("buildingId") Long buildingId)
    {
        List<ResponsiblePersonDTO> responsiblePersonDTOs = this.buildingService.getAllResponsiblePersons(buildingId);
        if(responsiblePersonDTOs == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(responsiblePersonDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteResponsiblePerson/{responsiblePersonId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteResponsiblePerson(@PathVariable("responsiblePersonId") Long responsiblePersonId)
    {
        Boolean deleted = buildingService.deleteResponsiblePerson(responsiblePersonId);
        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
