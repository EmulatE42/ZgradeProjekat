package com.ftn.ZgradeProjekat.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by djuro on 11/4/2017.
 */
@RestController
@RequestMapping(value = "/test")
@CrossOrigin
public class TestController
{
    @RequestMapping(value = "/hhh", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addOrder(){
        System.out.println("radiii");
    }
}
