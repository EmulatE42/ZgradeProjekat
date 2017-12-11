package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.*;
import com.ftn.ZgradeProjekat.domain.DTO.AnswerDTO;
import com.ftn.ZgradeProjekat.domain.DTO.BuildingDTO;
import com.ftn.ZgradeProjekat.domain.DTO.QuestionDTO;
import com.ftn.ZgradeProjekat.domain.DTO.SurveyDTO;
import com.ftn.ZgradeProjekat.service.AnswerService;
import com.ftn.ZgradeProjekat.service.QuestionService;
import com.ftn.ZgradeProjekat.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by EmulatE on 10-Dec-17.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/survey")
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;


    @RequestMapping(value = "/addSurvey", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SurveyDTO> addSurvey(@RequestBody SurveyDTO surveyDTO)
    {

        SurveyDTO saved = surveyService.save(surveyDTO);
        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/deleteSurvey/{surveyId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteSurvey(@PathVariable("surveyId") Integer Id)
    {
        try {
            surveyService.delete(Id);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAllSurveys", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SurveyDTO>> getAllSurveys()
    {
        List<SurveyDTO>  allSurveys = this.surveyService.getAllSurveys();
        if(allSurveys == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(allSurveys, HttpStatus.OK);
    }


    @RequestMapping(value = "/getSurveyById/{surveyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SurveyDTO> addSurvey(@PathVariable("surveyId") Integer id)
    {

        SurveyDTO s = surveyService.getById(id);
        if(s == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/getSurveyBetweenDates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SurveyDTO>> addAnswer(@RequestBody String datumi)
    { // Datumi 01.01.2017;05.05.2017
        String[] s = datumi.split(";");
        String startDateString = s[0];
        String endDateString = s[1];
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = df.parse(startDateString);
            endDate = df.parse(endDateString);
            String newDateString = df.format(startDate);
            String newDateString2 = df.format(endDate);
            System.out.println(newDateString);
            System.out.println(newDateString2);
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        List<SurveyDTO> rez = surveyService.getAllSurveysBetweenDates(startDate,endDate);
        if(rez == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(rez, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/getSurveyByIdofTenant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SurveyDTO>> addAnswer(@RequestBody Integer id)
    {

        List<SurveyDTO> s = surveyService.getAllSurveysFromTenant(id);
        if(s == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/getAllAnswers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnswerDTO>> getAllAnswers()
    {
        List<AnswerDTO>  allAnswers = this.answerService.getAllAnswers();
        if(allAnswers == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(allAnswers, HttpStatus.OK);
    }

    @RequestMapping(value = "/addAnswer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnswerDTO> addAnswer(@RequestBody AnswerDTO answerDTO)
    {

        AnswerDTO saved = answerService.save(answerDTO);
        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteAnswer/{answerId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteAnswer(@PathVariable("answerId") Integer Id)
    {
        try {
            answerService.delete(Id);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionDTO> addQuesiton(@RequestBody QuestionDTO questionDTO)
    {

        QuestionDTO saved = questionService.save(questionDTO);
        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteQuestion/{questionId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteQuestion(@PathVariable("questionId") Integer Id)
    {
        try {
            questionService.delete(Id);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }



}