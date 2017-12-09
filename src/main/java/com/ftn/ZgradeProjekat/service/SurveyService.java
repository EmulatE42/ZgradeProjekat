package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.Survey;
import com.ftn.ZgradeProjekat.domain.Tenant;

import java.util.Date;
import java.util.List;

/**
 * Created by EmulatE on 09-Dec-17.
 */
public interface SurveyService {

    Survey save(Survey survey);
    void delete(Integer id);
    List<Survey> getAllSurveys();
    Survey getById(Integer id);
    List<Survey> getAllSurveysBetweenDates(Date begin, Date end);
    List<Survey> getAllSurveysFromTenant(Integer id);

}
