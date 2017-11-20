package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.Apartment;
import com.ftn.ZgradeProjekat.domain.Building;
import com.ftn.ZgradeProjekat.domain.Location;
import com.ftn.ZgradeProjekat.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by djuro on 11/19/2017.
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

    @RequestMapping(value = "/findByLocationId/{apartmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Location> getLocationById(@PathVariable("locationId") Long locationId)
    {

        Location loc = this.locationService.getLocationById(locationId);

        if(loc == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(loc, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteLocation/{apartmentId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteLocation(@PathVariable("locationId") Long locationId)
    {

        Boolean deleted = this.locationService.deleteLocation(locationId);

        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
