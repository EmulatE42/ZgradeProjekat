package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.Parliament;
import com.ftn.ZgradeProjekat.service.ParliamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Momir on 17.11.2017.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/parlament")
public class ParliamentController {

    @Autowired
    private ParliamentService parliamentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parliament> addParlament(@RequestBody Parliament parliament)
    {
        Parliament saved = parliamentService.addParliament(parliament);
        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/getById/{parlamentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parliament> getById(@PathVariable("parlamentId") Long parlamentId)
    {
        Parliament parliament = this.parliamentService.getParliament(parlamentId);

        if(parliament == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(parliament, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteParlament/{buildingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteParlament(@PathVariable("parlamentId") Long parlamentId)
    {
        Boolean deleted = this.parliamentService.deleteParliament(parlamentId);
        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}