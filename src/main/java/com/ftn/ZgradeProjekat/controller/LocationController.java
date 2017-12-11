package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.DTO.LocationDTO;
import com.ftn.ZgradeProjekat.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by djuro on 11/19/2017.
 */

/**
 * Kontroler za rad sa lokacijama u zgradi
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/location")
public class LocationController
{
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService)
    {
        this.locationService = locationService;
    }

    /**
     *
     * @param locationId id lokacije za pretragu
     * @return pronadjeni objekat
     */
    @RequestMapping(value = "/findByLocationId/{locationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable("locationId") Long locationId)
    {

        LocationDTO loc = this.locationService.getLocationById(locationId);

        if(loc == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(loc, HttpStatus.OK);
    }

    /**
     *
     * @param locationId id lokacije za brisanje
     * @return da li je lokacija izbrisana
     */
    @RequestMapping(value = "/deleteLocation/{locationId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteLocation(@PathVariable("locationId") Long locationId)
    {

        Boolean deleted = this.locationService.deleteLocation(locationId);

        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    /**
     *
     * @param apartmentId id apartmana sa kojim povezujemo stanara
     * @param tenantId id starana
     * @return da li je povezivanje uspjesno
     */
    @RequestMapping(value = "/connectTenantAndApartment/{apartmentId}/{tenantId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> connectTenantAndApartment(@PathVariable("apartmentId") Long apartmentId, @PathVariable("tenantId") Integer tenantId)
    {
        Boolean connected = this.locationService.connectTenantAndApartment(apartmentId,tenantId);
        if(connected == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(connected, HttpStatus.OK);
    }
}
