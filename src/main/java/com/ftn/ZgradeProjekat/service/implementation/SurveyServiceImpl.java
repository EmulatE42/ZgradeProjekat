package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.DTO.SurveyDTO;
import com.ftn.ZgradeProjekat.domain.Survey;
import com.ftn.ZgradeProjekat.domain.Tenant;
import com.ftn.ZgradeProjekat.repository.SurveyRepository;
import com.ftn.ZgradeProjekat.repository.UserRepository;
import com.ftn.ZgradeProjekat.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            if (s.getDateOfSurvey().getTime() >= begin.getTime() && s.getDateOfSurvey().getTime() <= end.getTime())
            {
                ret.add(new SurveyDTO(s));
            }
        }
        return ret;
    }

}
