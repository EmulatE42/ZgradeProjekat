package com.ftn.ZgradeProjekat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by djuro on 11/4/2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
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

    @Column(name = "date_survey")
    private Date dateOfSurvey;

    @OneToOne
    private Tenant creator;

    @OneToMany
    private Set<Question> questions;


}
