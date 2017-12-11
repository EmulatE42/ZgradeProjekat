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


    @Override
    public SurveyDTO save(SurveyDTO surveyDto) {

        surveyRepository.save(new Survey(surveyDto));

        return surveyDto;
    }

    @Override
    public void delete(Integer id) {
        surveyRepository.delete(id);
    }

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

    @Override
    public SurveyDTO getById(Integer id) {


        Survey s = surveyRepository.findOne(id);

        return new SurveyDTO(s);
    }

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

    @Override
    public List<SurveyDTO> getAllSurveysFromTenant(Integer id) {
        List<Survey> surveys = surveyRepository.findAll();
        List<SurveyDTO> ret = new ArrayList<>();
        for (Survey s : surveys)
        {
            if (s.getCreator().getId().equals(id))
            {
                ret.add(new SurveyDTO(s));
            }
        }
        return ret;
    }


}
