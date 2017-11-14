package com.ftn.ZgradeProjekat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
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
@Getter
@Setter
@Table(name = "parlament")
public class Parlament
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parlament_id", unique = true, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "parlament", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Session> sessions = new HashSet<>();
}
