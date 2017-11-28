package com.ftn.ZgradeProjekat.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by djuro on 11/4/2017.
 */
@Getter
@Setter
@Entity
@Table(name = "apartment")
public class Apartment extends Location
{
    @Column(name = "apartment_floor")
    private Long floor;

    @Column(name = "apartment_square")
    private Long square;

    //treba promjeniti postaviti Stanar klasu
    //ManyToOne
    @ManyToMany
    //@JoinColumn(referencedColumnName = "id", name = "apartment_user_id")
    private Set<User> owners;

    public void addAddTenant(User user)
    {
        this.owners.add(user);
    }
}
