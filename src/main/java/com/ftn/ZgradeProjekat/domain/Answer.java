package com.ftn.ZgradeProjekat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by EmulatE on 09-Dec-17.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bug_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "text")
    private String text; // ako jep prvi

    @Column(name = "rate")
    private int rate; // ako je drugi

    @Column(name = "choiced")
    private String choiced; // ako je treci tip


}
