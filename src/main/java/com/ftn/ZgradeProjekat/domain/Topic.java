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
@Table(name = "topic")
public class Topic
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "topic_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "topic_description")
    private String description;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "topic_user_id")
    private User creator;

    @Column(name = "topic_accepted")
    private Boolean accepted;
}
