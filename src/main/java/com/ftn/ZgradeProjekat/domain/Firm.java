package com.ftn.ZgradeProjekat.domain;

import com.ftn.ZgradeProjekat.domain.DTO.RegisterUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by EmulatE on 26-Nov-17.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "firm")

public class Firm extends User
{
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "firm_name")
    private String name;

    @Column(name = "firm_description")
    private String description;

    @OneToMany
    private Set<Bug> bugs; //brisanjem buga brise i ovde

    public Firm(RegisterUserDTO registerUserDTO)
    {
        this.setUsername(registerUserDTO.getUsername());
        this.setPassword(registerUserDTO.getPassword());
        this.setAddress(registerUserDTO.getAddress());
        this.setName(registerUserDTO.getFirmName());
        this.setDescription(registerUserDTO.getFirmDescription());
        this.bugs = new HashSet<>();
    }

    public void addBug(Bug bug)
    {
        this.bugs.add(bug);
    }

    public void removeBug(Bug bug)
    {
        this.bugs.remove(bug);
    }
}