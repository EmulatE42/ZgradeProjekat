package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.Building;
import com.ftn.ZgradeProjekat.domain.DTO.BugDTO;
import com.ftn.ZgradeProjekat.domain.DTO.BuildingDTO;
import com.ftn.ZgradeProjekat.domain.DTO.CommentDTO;
import com.ftn.ZgradeProjekat.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by djuro on 11/29/2017.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/bug")
public class BugController
{
    @Autowired
    BugService bugService;

    @RequestMapping(value = "/reportBug/{locationId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BugDTO> reportBug(@PathVariable("locationId") Long locationId, @RequestBody BugDTO bugDTO)
    {
        BugDTO savedBug = this.bugService.reportBug(locationId, bugDTO);
        if(savedBug == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(savedBug, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAllBugs/{locationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BugDTO>> getAllBugs(@PathVariable("locationId") Long locationId)
    {
        List<BugDTO> bugDTOs = this.bugService.getAllBugs(locationId);

        if(bugDTOs == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(bugDTOs, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteBug/{bugId}/{locationId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteBug(@PathVariable("bugId") Long bugId, @PathVariable("locationId") Long locationId)
    {
        Boolean deleted = this.bugService.deleteBug(bugId, locationId);
        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @RequestMapping(value = "/addComment/{bugId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDTO> addComment(@PathVariable("bugId") Long bugId, @RequestBody CommentDTO commentDTO)
    {
        CommentDTO savedComment = this.bugService.addComment(commentDTO, bugId);
        if(savedComment == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getBug/{bugId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BugDTO> getBug(@PathVariable("bugId") Long bugId)
    {
        BugDTO bugDTO = this.bugService.getBug(bugId);
        if(bugDTO == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(bugDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteComment/{commentId}/{bugId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteComment(@PathVariable("commentId") Long commentId, @PathVariable("bugId") Long bugId)
    {
        Boolean deleted = this.bugService.deleteComment(commentId,bugId);
        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @RequestMapping(value = "/getBugsOfResponsiblePerson/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BugDTO>> getBugsOfResponsiblePerson(@PathVariable("userId") Integer userId)
    {
        List<BugDTO> bugs = this.bugService.getBugsOfResponsiblePerson(userId);
        if(bugs == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bugs, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/connectBugAndFirm/{bugId}/{firmId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> connectBugAndFirm(@PathVariable("bugId") Long bugId, @PathVariable("firmId") Integer firmId)
    {
        Boolean connected = this.bugService.connectBugAndFirm(bugId, firmId);

        if(connected == false)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(connected, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getBugsOfFirm/{firmId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BugDTO>> getBugsOfFirm(@PathVariable("firmId") Integer firmId)
    {
        List<BugDTO> bugDTOs = this.bugService.getBugsOfFirm(firmId);
        if(bugDTOs == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bugDTOs, HttpStatus.CREATED);
    }
}
