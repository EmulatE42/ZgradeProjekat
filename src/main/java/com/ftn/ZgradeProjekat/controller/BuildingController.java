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

/**
 * Kontroler za rad sa zgradama
 */
@RestController
@CrossOrigin(allowedHeaders={"x-auth-token"})
@RequestMapping(value = "/building")
public class BuildingController
{
    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService)
    {
        this.buildingService = buildingService;
    }

    /**
     *
     * @param building DTO objekat nove zgrade koju kreiramo
     * @return kreirani objekat
     */
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

    /**
     *
     * @param buildingId id zgrade koju zelimo da vratimo
     * @return DTO objekat pronadjene zgrade
     */
    @RequestMapping(value = "/findByBuildingId/{buildingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuildingDTO> getById(@PathVariable("buildingId") Long buildingId)
    {
        BuildingDTO rest = this.buildingService.getById(buildingId);

        if(rest == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(rest, HttpStatus.OK);
    }

    /**
     *
     * @return vracamo sve zgrade
     */
    @RequestMapping(value = "/getAllBuildings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BuildingListItemDTO>> getAllBuildings()
    {
        List<BuildingListItemDTO> buildingDTOs = buildingService.getBuildings();
        return new ResponseEntity<>(buildingDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param buildingId id objekta kog zelimo izbrisati
     * @return da li je objekat uspjesno izbrisan
     */
    @RequestMapping(value = "/deleteBuilding/{buildingId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteBuilding(@PathVariable("buildingId") Long buildingId)
    {
        Boolean deleted = buildingService.deleteBuilding(buildingId);
        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    /**
     *
     * @param locationDTO dto objekat locationa koji zelimo kreirati
     * @return dto kreiranog objekta
     */
    @RequestMapping(value = "/addLocationToBuilding", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationDTO> addLocation(@RequestBody LocationDTO locationDTO)
    {
        LocationDTO savedLocationDTO = buildingService.addLocationToBuilding(locationDTO);
        return new ResponseEntity<>(savedLocationDTO, HttpStatus.OK);
    }

    /**
     *
     * @param buildingId id zgrade u koju dodajemo novo odgovorno lice
     * @param responsiblePersonDTO DTO objekat novog odgovornog lica koje zelimo kreirati
     * @return dto objekat kreiranog objekta
     */
    @RequestMapping(value = "/addResponsiblePerson/{buildingId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponsiblePersonDTO> addResponsiblePerson(@PathVariable("buildingId") Long buildingId, @RequestBody ResponsiblePersonDTO responsiblePersonDTO)
    {
        ResponsiblePersonDTO savedResponsiblePerson = this.buildingService.addResponsiblePerson(responsiblePersonDTO, buildingId);
        if(savedResponsiblePerson == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(savedResponsiblePerson, HttpStatus.OK);
    }

    /**
     *
     * @param buildingId id zgrade za koju zelimo listu odgovornih lica
     * @return lista odgovornih lica
     */
    @RequestMapping(value = "/getAllResponsiblePersons/{buildingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponsiblePersonDTO>> getAllResponsiblePersons(@PathVariable("buildingId") Long buildingId)
    {
        List<ResponsiblePersonDTO> responsiblePersonDTOs = this.buildingService.getAllResponsiblePersons(buildingId);
        if(responsiblePersonDTOs == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(responsiblePersonDTOs, HttpStatus.OK);
    }

    /**
     *
     * @param responsiblePersonId id odgovornog lica koje brisemo
     * @param buildingId id zgrade u kome se nalazi odgovorno lice
     * @return da li je odgovorno lice izbrisano
     */
    @RequestMapping(value = "/deleteResponsiblePerson/{responsiblePersonId}/{buildingId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteResponsiblePerson(@PathVariable("responsiblePersonId") Long responsiblePersonId, @PathVariable("buildingId") Long buildingId)
    {
        Boolean deleted = buildingService.deleteResponsiblePerson(responsiblePersonId, buildingId);
        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    /**
     *
     * @param buildingId id zgrade u kojoj zelimo postaviti novog predsjednika
     * @param tenantId id stanara koji postaje novi predsjednik zgrade
     * @return da li je predsjednik postavljen
     */
    @RequestMapping(value = "/setBuildingManager/{buildingId}/{tenantId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> setBuildingManager(@PathVariable("buildingId") Long buildingId, @PathVariable("tenantId") Integer tenantId)
    {
        Boolean saved = this.buildingService.setBuildingManager(buildingId,tenantId);
        if(saved == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    /**
     *
     * @param buildingId id location-a za koji zelimo sva odgovorna lica
     * @return lista odgovornih lica
     */
    @RequestMapping(value = "/getAllResponsiblePersonsByLocationId/{buildingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponsiblePersonDTO>> getAllResponsiblePersonsByLocationId(@PathVariable("buildingId") Long buildingId)
    {
        List<ResponsiblePersonDTO> responsiblePersonDTOs = this.buildingService.getAllResponsiblePersonsByLocationId(buildingId);
        if(responsiblePersonDTOs == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(responsiblePersonDTOs, HttpStatus.OK);
    }
}
