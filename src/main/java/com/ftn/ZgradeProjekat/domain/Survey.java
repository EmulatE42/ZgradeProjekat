package com.ftn.ZgradeProjekat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(name = "bug_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "survey_description")
    private String description;

    @OneToMany
    private Set<Question> questions;


}
