package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.DTO.SessionDTO;
import com.ftn.ZgradeProjekat.domain.DTO.TenantDTO;
import com.ftn.ZgradeProjekat.domain.Session;
import com.ftn.ZgradeProjekat.service.SessionService;
import com.ftn.ZgradeProjekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

/**
 * Created by Momir on 28.11.2017.
 */

/**
 * REST controller for managing Session
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    /**
     * POST  /add : Create a new session.
     *
     * @param session
     * @return the ResponseEntity with status 201 (Created) and with body the new session, or with status 409 (Conflict) if the session has already an ID
     */

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Session> addSession(@RequestBody Session session)
    {
        Session saved = sessionService.addSession(session);

        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * GET  /getById/{sessionId} : Returning a session which has sessionId
     *
     * @param sessionId
     * @return the ResponseEntity with status 200 (OK) and with body the new session, or with status 404 (Not Found) if the session hasn't a sessionId
     */


    @RequestMapping(value = "/getById/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Session> getById(@PathVariable("sessionId") Long sessionId)
    {
        Session session = this.sessionService.getSession(sessionId);

        if(session == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(session, HttpStatus.OK);
    }

    /**
     * GET  /deleteSession/{sessionId} : Deleting a session which has a sessionId
     *
     * @param sessionId
     * @return the ResponseEntity with status 200 (OK) and with body the new session, or with status 404 (Not Found) if the session hasn't a sessionId
     */

    @RequestMapping(value = "/deleteSession/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteSession(@PathVariable("sessionId") Long sessionId)
    {
        Boolean deleted = this.sessionService.deleteSession(sessionId);
        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    /**
     * GET  /getSessions : Returning a list of sessions
     *
     * @return the ResponseEntity with status 200 (OK) and with body the new sessions, or with status 404 (Not Found) if the sessions doesn't exist
     */

    @RequestMapping(value = "/getSessions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Session>> getSessions()
    {
        List<Session> sessions = this.sessionService.getSessions();

        if(sessions == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @RequestMapping(value = "/isBuildingManager/{tenantId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TenantDTO> isBuildingManager(@PathVariable("tenantId") Integer tenantId)
    {
        TenantDTO tenantDTO = this.userService.getTenant(tenantId);


        if(tenantDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        else if(tenantDTO.getIsBuildingManager() == false) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        else {
            return new ResponseEntity<>(tenantDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/updateRecord", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionDTO> updateRecord(@RequestBody SessionDTO session)
    {
        Boolean updated = sessionService.updateRecord(session.getId(), session.getRecord());

        if(updated == false)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(session, HttpStatus.OK);
    }
}
