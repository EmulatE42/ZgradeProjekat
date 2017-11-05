package com.ftn.ZgradeProjekat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by djuro on 11/4/2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
@Table(name = "question")
public class Question
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bug_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "question_text")
    private String text;

    @Column(name = "question_votes")
    private Long votes;
}
