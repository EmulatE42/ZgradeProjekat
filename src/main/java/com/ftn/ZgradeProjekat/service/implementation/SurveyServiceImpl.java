package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.DTO.FilledSurveyDTO;
import com.ftn.ZgradeProjekat.domain.DTO.SurveyDTO;
import com.ftn.ZgradeProjekat.domain.FilledSurvey;
import com.ftn.ZgradeProjekat.domain.Survey;
import com.ftn.ZgradeProjekat.domain.Tenant;
import com.ftn.ZgradeProjekat.repository.FilledSurveyRepository;
import com.ftn.ZgradeProjekat.repository.QuestionRepository;
import com.ftn.ZgradeProjekat.repository.SurveyRepository;
import com.ftn.ZgradeProjekat.repository.UserRepository;
import com.ftn.ZgradeProjekat.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by EmulatE on 09-Dec-17.
 */
@Service
public class SurveyServiceImpl implements SurveyService{

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    FilledSurveyRepository filledSurveyRepository;

    /**
     *
     * @param surveyDto - anketa koja se snima
     * @return - snimljena anketa
     */
    @Override
    public SurveyDTO save(SurveyDTO surveyDto) {

        surveyRepository.save(new Survey(surveyDto));

        return surveyDto;
    }

    /**
     *
     * @param id - id koja se anketa brise
     */
    @Override
    public void delete(Long id) {
        surveyRepository.delete(id);
    }

    /**
     *
     * @return - lista svih anketa
     */

    @Override
    public List<SurveyDTO> getAllSurveys() {


        List<Survey> s =  surveyRepository.findAll();
        List<SurveyDTO> ret = new ArrayList<>();

        for (Survey s1 : s)
        {
            ret.add(new SurveyDTO(s1));
        }
        return ret;

    }

    /**
     *
     * @param id - id ankete koja se dobavlja
     * @return - anketa po idu
     */
    @Override
    public SurveyDTO getById(Long id) {

        Survey s = surveyRepository.findOne(id);

        return new SurveyDTO(s);
    }

    /**
     *
     * @param begin - datum pocetka objave
     * @param end - datum kraja objave
     * @return - sve ankete koje su u intervalu
     */
    @Override
    public List<SurveyDTO> getAllSurveysBetweenDates(Date begin, Date end) {
        List<Survey> surveys = surveyRepository.findAll();
        List<SurveyDTO> ret = new ArrayList<>();
        for (Survey s : surveys)
        {

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;
            try {
                date = df.parse(s.getDateOfSurvey());
                String newDateString = df.format(date);

                System.out.println(newDateString);
            } catch (ParseException e) {

            }
            if (date.getTime() >= begin.getTime() && date.getTime() <= end.getTime())
            {
                ret.add(new SurveyDTO(s));
            }
        }
        return ret;
    }

    @Override
    public List<SurveyDTO> getAllFilledSurveys(Long tenantID) {
        List<Survey> svi = surveyRepository.findAll();
        List<FilledSurvey> sviP = filledSurveyRepository.findAll();
        List<SurveyDTO> rez = new ArrayList<>();

        for (Survey s : svi)
        {
            for (FilledSurvey fs : sviP)
            {
                if (fs.getTenantID().equals(tenantID) && s.getId().equals(fs.getSurveyID()))
                {
                    rez.add(new SurveyDTO(s));
                }
            }
        }
        return rez;
    }

    @Override
    public FilledSurveyDTO save(FilledSurveyDTO filledSurveyDTO) {
        filledSurveyRepository.save(new FilledSurvey(filledSurveyDTO));
        return filledSurveyDTO;
    }

}
