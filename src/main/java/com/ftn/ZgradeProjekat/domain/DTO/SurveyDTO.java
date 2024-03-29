package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.Question;
import com.ftn.ZgradeProjekat.domain.Survey;
import com.ftn.ZgradeProjekat.domain.Tenant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by EmulatE on 10-Dec-17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDTO {

    private Long id;
    private String description;
    private Long buildingId;
    private String dateOfSurvey;
    private Set<QuestionDTO> questions;

    public SurveyDTO(Survey survey)
    {
        this.questions = new HashSet<>();
        this.id = survey.getId();
        this.buildingId = survey.getBuildingId();
        this.description= survey.getDescription();
        this.dateOfSurvey = survey.getDateOfSurvey();
        if (survey.getQuestions() != null) {
            for (Question q : survey.getQuestions()) {
                questions.add(new QuestionDTO(q));
            }
        }
    }


}
