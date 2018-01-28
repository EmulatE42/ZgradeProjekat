package com.ftn.ZgradeProjekat.domain;

import com.ftn.ZgradeProjekat.domain.DTO.QuestionDTO;
import com.ftn.ZgradeProjekat.domain.DTO.SurveyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by djuro on 11/4/2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "survey")
public class Survey
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "survey_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "survey_description")
    private String description;

    @Column(name = "buildingId")
    private Long buildingId;

    @Column(name = "date_survey")
    private String dateOfSurvey;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "q1")
    private Set<Question> questions;

    public Survey(SurveyDTO surveyDTO)
    {

        this.questions = new HashSet<>();
        this.description = surveyDTO.getDescription();
        this.dateOfSurvey = surveyDTO.getDateOfSurvey();
        this.buildingId = surveyDTO.getBuildingId();
        for (QuestionDTO q : surveyDTO.getQuestions())
        {
            this.questions.add(new Question(q));
        }

    }

}
