package com.ftn.ZgradeProjekat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


/**
 * Created by djuro on 11/4/2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Table(name = "location")
public abstract class Location
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "location_id")
    protected Long id;

    @OneToMany
    protected Set<Bug> bugs;

    @ManyToOne
    protected Building building;
}
