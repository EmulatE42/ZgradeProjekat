package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.Building;
import com.ftn.ZgradeProjekat.domain.Parlament;
import com.ftn.ZgradeProjekat.service.ParlamentService;
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
public class ParlamentController {

    @Autowired
    private ParlamentService parlamentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parlament> addParlament(@RequestBody Parlament parlament)
    {
        Parlament saved = parlamentService.addParlament(parlament);
        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/getById/{parlamentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parlament> getById(@PathVariable("parlamentId") Long parlamentId)
    {
        Parlament parlament = this.parlamentService.getParlament(parlamentId);

        if(parlament == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(parlament, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteParlament/{buildingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteParlament(@PathVariable("parlamentId") Long parlamentId)
    {
        Boolean deleted = this.parlamentService.deleteParlament(parlamentId);
        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
