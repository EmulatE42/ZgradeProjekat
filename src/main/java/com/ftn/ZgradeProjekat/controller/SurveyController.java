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

/**
 * Kontroler za rad sa anketama
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

    /**
     *
     * @param surveyDTO - anketa koja se upisuje
     * @return - upisana anketa
     */
    @RequestMapping(value = "/addSurvey", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SurveyDTO> addSurvey(@RequestBody SurveyDTO surveyDTO)
    {
        System.out.println("LOM LOM");
        System.out.println(surveyDTO.getId());
        System.out.println(surveyDTO.getDescription());
        System.out.println(surveyDTO.getBuildingId());
        System.out.println(surveyDTO.getDateOfSurvey());
        System.out.println(surveyDTO.getQuestions() == null);
        System.out.println(surveyDTO.getQuestions().size());
        /*
         private Long id;
    private String description;
    private Long buildingId;
    private String dateOfSurvey;
    private Set<QuestionDTO> questions;
        * */


        SurveyDTO saved = surveyService.save(surveyDTO);
        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    /**
     *
     * @param Id id ankete koja se brise
     * @return bool kao obavestenje da li je uspesno obrisana
     */
    @RequestMapping(value = "/deleteSurvey/{surveyId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteSurvey(@PathVariable("surveyId") Long Id)
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

    /**
     *
     * @return lista svih anketa
     */
    @RequestMapping(value = "/getAllSurveys", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SurveyDTO>> getAllSurveys()
    {
        List<SurveyDTO>  allSurveys = this.surveyService.getAllSurveys();
        if(allSurveys == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(allSurveys, HttpStatus.OK);
    }

    /**
     *
     * @param id id ankete
     * @return - pronadjena anketa, ili greska ako nismo uspeli da pronadjemo
     */
    @RequestMapping(value = "/getSurveyById/{surveyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SurveyDTO> getSurveyById(@PathVariable("surveyId") Long id)
    {

        SurveyDTO s = surveyService.getById(id);
        if(s == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    /**
     *
     * @param datumi - string koji sadrzi oba datuma ciji se interval trazi
     * @return - lista anketa koja zadovoljava uslov
     */
    @RequestMapping(value = "/getSurveyBetweenDates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SurveyDTO>> getSurveyBetweenDates(@RequestBody String datumi)
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

    /**
     *
     * @return svi odgovori
     */
    @RequestMapping(value = "/getAllAnswers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnswerDTO>> getAllAnswers()
    {
        List<AnswerDTO>  allAnswers = this.answerService.getAllAnswers();
        if(allAnswers == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(allAnswers, HttpStatus.OK);
    }

    /**
     *
     * @return -- sva pitanja
     */
    @RequestMapping(value = "/getAllQuestions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuestionDTO>> getAllQuestions()
    {
        List<QuestionDTO>  allAnswers = this.questionService. getAllQuestions();
        if(allAnswers == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(allAnswers, HttpStatus.OK);
    }

    /**
     *
     * @param answerDTO - odgovor koje se dodaje
     * @return - upisano pitanje
     */
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

    /**
     *
     * @param Id - id koji se odgovor brise
     * @return - bool u zavisnosti da li je brisanje uspesno
     */
    @RequestMapping(value = "/deleteAnswer/{answerId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteAnswer(@PathVariable("answerId") Long Id)
    {
        try {
            answerService.delete(Id);
        }
        catch(Exception e)
        {
            System.out.print(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.print("Prosao");
        Boolean a = true;
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    /**
     *
     * @param questionDTO - pitanje koje se dodaje
     * @return - dodato pitanje
     */
    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionDTO> addQuesiton(@RequestBody QuestionDTO questionDTO)
    {
        System.out.println("DOBIO SAM ");
        System.out.println(questionDTO.getText());
        System.out.println(questionDTO.getChoices());
        System.out.println();
        QuestionDTO saved = questionService.save(questionDTO);
        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     *
     * @param Id - id pitanja koje se brise
     * @return - bool u zavinosti da li je brisanje uspesno
     */
    @RequestMapping(value = "/deleteQuestion/{questionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteQuestion(@PathVariable("questionId") Long Id)
    {
        try {
            questionService.delete(Id);
        }
        catch(Exception e)
        {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/updateAnswer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateAnswer(@RequestBody String s)
    {
        System.out.println("DOBIO SAM " + s);
        Long a,id;
        String temp[] = s.split(";");
        System.out.println(temp[0] + ";" + temp[1]);
        a = Long.parseLong(temp[0].replace('\"',' ').trim());
        id = Long.parseLong(temp[1].replace('\"',' ').trim());
        System.out.println("SALJEM SA " + a + " " + id);
        answerService.updateAnswer(a,id);


        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }



}
