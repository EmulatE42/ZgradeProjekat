package com.ftn.ZgradeProjekat.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.*;

/**
 * Created by djuro on 11/4/2017.
 */
@Getter
@Setter
@Entity
@Table(name = "apartment")
public class Apartment extends Location
{
    @Column(name = "apartment_flor")
    private Long flor;

    @Column(name = "apartment_square")
    private Long square;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "apartment_user_id")
    private User owner;
}
