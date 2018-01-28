package com.ftn.ZgradeProjekat.domain;

import com.ftn.ZgradeProjekat.domain.DTO.FilledSurveyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "filledSurvey")

public class FilledSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "filledSurveyID", unique = true, nullable = false)
    private Long id;

    @Column(name = "surveyID")
    private Long surveyID;

    @Column(name = "tenantID")
    private Long tenantID;


    public FilledSurvey(FilledSurveyDTO filledSurveyDTO)
    {
        this.id = filledSurveyDTO.getId();
        this.surveyID = filledSurveyDTO.getSurveyID();
        this.tenantID = filledSurveyDTO.getTenantID();
    }

}