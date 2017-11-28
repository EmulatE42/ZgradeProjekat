package com.ftn.ZgradeProjekat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Momir on 14.11.2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tenant")
public class Tenant extends User {

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    //@ManyToOne
    @ManyToMany
    private Set<Apartment> apartments;

    public Tenant(User user)
    {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserAuthorities(user.getUserAuthorities());
    }

    public void addApartment(Apartment apartment)
    {
        this.apartments.add(apartment);
    }

    public void removeApartment(Apartment apartment)
    {
        this.apartments.remove(apartment);
    }
}
