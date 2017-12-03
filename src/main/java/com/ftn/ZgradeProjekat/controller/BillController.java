package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.Bill;
import com.ftn.ZgradeProjekat.domain.DTO.BugDTO;
import com.ftn.ZgradeProjekat.service.BillService;
import com.ftn.ZgradeProjekat.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by djuro on 12/2/2017.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/bill")
public class BillController
{
    @Autowired
    BillService billService;

    @Autowired
    BugService bugService;

    @RequestMapping(value = "/makeBill/{bugId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bill> makeBill(@PathVariable("bugId") Long bugId, @RequestBody Bill billDTO)
    {
        Bill bill = this.billService.makeBill(bugId, billDTO);
        if(bill == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(bill, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getBill/{bugId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bill> getBill(@PathVariable("bugId") Long bugId)
    {
        Bill bill = this.billService.getBill(bugId);
        if(bill == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(bill, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/payBill/{bugId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> payBill(@PathVariable("bugId") Long bugId)
    {
        Boolean paid = this.bugService.payBill(bugId);
        if(paid == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(paid, HttpStatus.CREATED);
    }


}
