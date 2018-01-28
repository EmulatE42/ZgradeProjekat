package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.DTO.PublicNotificationDTO;
import com.ftn.ZgradeProjekat.service.PublicNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by EmulatE on 26-Jan-18.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/publicNotification")
public class PublicNotificationController {

    @Autowired
    PublicNotificationService publicNotificationService;

    @RequestMapping(value = "/addPn", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublicNotificationDTO> addSurvey(@RequestBody PublicNotificationDTO publicNotificationDTO)
    {

        PublicNotificationDTO saved = publicNotificationService.save(publicNotificationDTO);
        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAllPns", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PublicNotificationDTO>> getAllSurveys()
    {
        List<PublicNotificationDTO> allSurveys = this.publicNotificationService.getAllPublicNotifications();

        if(allSurveys == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(allSurveys, HttpStatus.OK);
    }

}

