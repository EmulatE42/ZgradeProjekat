package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.DTO.FilledSurveyDTO;
import com.ftn.ZgradeProjekat.domain.DTO.SurveyDTO;
import com.ftn.ZgradeProjekat.domain.FilledSurvey;
import com.ftn.ZgradeProjekat.domain.Survey;
import com.ftn.ZgradeProjekat.domain.Tenant;

import java.util.Date;
import java.util.List;

/**
 * Created by EmulatE on 09-Dec-17.
 */
public interface SurveyService {

    SurveyDTO save(SurveyDTO surveyDTO);
    void delete(Long id);
    List<SurveyDTO> getAllSurveys();
    SurveyDTO getById(Long id);
    List<SurveyDTO> getAllSurveysBetweenDates(Date begin, Date end);
    List<SurveyDTO> getAllFilledSurveys(Long tenantID);
    FilledSurveyDTO save(FilledSurveyDTO filledSurveyDTO);

}
