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
@Table(name = "parlament")
public class Parlament
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parlament_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "parlament_date")
    private Date date;

    @OneToMany
    private Set<Topic> topics;

    @Column(name = "parlament_record")
    private String record;

    @Column(name = "parlament_timetable")
    private String timetable;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "parlament_user_id")
    private User creator;
}
