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
@Table(name = "bug")
public class Bug
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bug_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "bug_description")
    private String description;

    @Column(name = "bug_date_of_bug")
    private Date dateOfBug;

    @OneToMany
    private Set<Comment> comments;

    @Column(name = "bug_finished")
    private Boolean finished;
}
