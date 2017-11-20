package com.ftn.ZgradeProjekat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Momir on 14.11.2017.
 */

@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "admin")
public class Admin extends User {

    @OneToMany
    private Set<Building> buildings = new HashSet<Building>();

    public Admin(User user)
    {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserAuthorities(user.getUserAuthorities());
    }
}
