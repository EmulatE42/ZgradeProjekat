package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.DTO.ParliamentDTO;
import com.ftn.ZgradeProjekat.domain.Parliament;
import com.ftn.ZgradeProjekat.domain.Session;
import com.ftn.ZgradeProjekat.service.ParliamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Momir on 17.11.2017.
 */

/**
 * REST controller for managing Parlament
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/parlament")
public class ParliamentController {

    @Autowired
    private ParliamentService parliamentService;

    /**
     * POST  /add : Create a new parlament.
     *
     * @param parliament
     * @return the ResponseEntity with status 201 (Created) and with body the new parliament, or with status 409 (Conflict) if the parliament has already an ID
     */

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

    /**
     * GET  /getById/{parlamentId} : Returning a parlament which has parlamentId
     *
     * @param parlamentId
     * @return the ResponseEntity with status 200 (OK) and with body the new parliament, or with status 404 (Not Found) if the parliament hasn't a parlamentId
     */

    @RequestMapping(value = "/getById/{parlamentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parliament> getById(@PathVariable("parlamentId") Long parlamentId)
    {
        Parliament parliament = this.parliamentService.getParliament(parlamentId);

        if(parliament == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(parliament, HttpStatus.OK);
    }

    /**
     * GET  /deleteParlament/{parlamentId} : Deleting a parlament which has parlamentId
     *
     * @param parlamentId
     * @return the ResponseEntity with status 200 (OK) and with body the new parliament, or with status 404 (Not Found) if the parliament hasn't a parlamentId
     */

    @RequestMapping(value = "/deleteParlament/{parlamentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteParlament(@PathVariable("parlamentId") Long parlamentId)
    {
        Boolean deleted = this.parliamentService.deleteParliament(parlamentId);
        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    /**
     * GET  /getParlaments : Returning a list of parlaments
     *
     * @return the ResponseEntity with status 200 (OK) and with body the new parliaments, or with status 404 (Not Found) if the parliaments doesn't exist
     */

    @RequestMapping(value = "/getParlaments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Parliament>> getParlaments()
    {
        List<Parliament> parliaments = this.parliamentService.getParliaments();

        if(parliaments == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(parliaments, HttpStatus.OK);
    }

    /**
     * GET  /getSessions/{parlamentId} : Returning a list of sessions which contain parlament with parlamentId
     *
     * @return the ResponseEntity with status 200 (OK) and with body the new Set<Session> sessions, or with status 404 (Not Found) if the parliament with parlamentId doesn't exist
     */

    @RequestMapping(value = "/getSessions/{parlamentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Session>> getSessions(@PathVariable("parlamentId") Long parlamentId)
    {
        Parliament parliament = this.parliamentService.getParliament(parlamentId);

        if(parliament == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            return new ResponseEntity<>(parliament.getSessions(), HttpStatus.OK);
        }
    }

}
