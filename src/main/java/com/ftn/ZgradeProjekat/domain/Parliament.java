package com.ftn.ZgradeProjekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "parliament")
public class Parliament
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parliament_id", unique = true, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "parliament", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Session> sessions = new HashSet<>();
}
