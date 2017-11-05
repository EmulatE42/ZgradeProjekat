package com.ftn.ZgradeProjekat.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by djuro on 11/4/2017.
 */
@Getter
@Setter
@Entity
@Table(name = "hallway")
public class Attic extends Location
{
    @Column(name = "hallway_flor")
    private Long flor;

    @Column(name = "hallway_square")
    private Long square;
}
