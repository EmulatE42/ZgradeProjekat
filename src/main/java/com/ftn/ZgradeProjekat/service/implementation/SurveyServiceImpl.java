package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Survey;
import com.ftn.ZgradeProjekat.domain.Tenant;
import com.ftn.ZgradeProjekat.repository.SurveyRepository;
import com.ftn.ZgradeProjekat.repository.UserRepository;
import com.ftn.ZgradeProjekat.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by EmulatE on 09-Dec-17.
 */
public class SurveyServiceImpl implements SurveyService{

    @Autowired
    SurveyRepository surveyRepository;


    @Override
    public Survey save(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public void delete(Integer id) {
        surveyRepository.delete(id);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getById(Integer id) {
        return surveyRepository.findOne(id);
    }

    @Override
    public List<Survey> getAllSurveysBetweenDates(Date begin, Date end) {
        List<Survey> surveys = surveyRepository.findAll();
        List<Survey> ret = new ArrayList<>();
        for (Survey s : surveys)
        {
            if (s.getDateOfSurvey().getTime() >= begin.getTime() && s.getDateOfSurvey().getTime() <= end.getTime())
            {
                ret.add(s);
            }
        }
        return ret;
    }

    @Override
    public List<Survey> getAllSurveysFromTenant(Integer id) {
        List<Survey> surveys = surveyRepository.findAll();
        List<Survey> ret = new ArrayList<>();
        for (Survey s : surveys)
        {
            if (s.getCreator().getId().equals(id))
            {
                ret.add(s);
            }
        }
        return ret;
    }


}
