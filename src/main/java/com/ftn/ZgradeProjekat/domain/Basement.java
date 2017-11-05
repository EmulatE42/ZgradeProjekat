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
@Table(name = "basement")
public class Basement extends Location
{
    @Column(name = "basement_square")
    private Long square;
}
