package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.Parliament;
import com.ftn.ZgradeProjekat.domain.Session;
import com.ftn.ZgradeProjekat.service.ParliamentService;
import com.ftn.ZgradeProjekat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Momir on 28.11.2017.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ParliamentService parliamentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Session> addSession(@RequestBody Session session)
    {
        Session saved = sessionService.addSession(session);

        if(session.getParliament() != null)
        {
            System.out.println("Nije null");

        }
        else
            System.out.println("Jeste null");

        //Parliament parliament = parliamentService.getParliament(session.getParliament().getId());
        //parliament.getSessions().add(session);

        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/getById/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Session> getById(@PathVariable("sessionId") Long sessionId)
    {
        Session session = this.sessionService.getSession(sessionId);

        if(session == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteSession/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteSession(@PathVariable("sessionId") Long sessionId)
    {
        Boolean deleted = this.sessionService.deleteSession(sessionId);
        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @RequestMapping(value = "/getSessions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Session>> getSessions()
    {
        List<Session> sessions = this.sessionService.getSessions();

        if(sessions == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(sessions, HttpStatus.OK);
    }
}
