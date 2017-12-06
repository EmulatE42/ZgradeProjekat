package com.ftn.ZgradeProjekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.ZgradeProjekat.domain.DTO.RegisterUserDTO;
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
    @JsonIgnore
    private Set<Apartment> apartments;

    @Column(name = "tenant_is_building_manager")
    private Boolean isBuildingmManager;

    public Tenant(User user)
    {
        this.setId(user.getId());  //--------------
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserAuthorities(user.getUserAuthorities());
    }

    public Tenant(RegisterUserDTO registerUserDTO)
    {
        this.setUsername(registerUserDTO.getUsername());
        this.setPassword(registerUserDTO.getPassword());
        this.setFirstname(registerUserDTO.getFirstname());
        this.setLastname(registerUserDTO.getLastname());
        this.setIsBuildingmManager(false);
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
