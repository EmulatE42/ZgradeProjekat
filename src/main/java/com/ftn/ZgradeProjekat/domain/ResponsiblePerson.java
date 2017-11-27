package com.ftn.ZgradeProjekat.domain;

import javax.persistence.Entity;

/**
 * Created by Momir on 14.11.2017.
 */
@Entity
public class ResponsiblePerson extends User {

    public ResponsiblePerson(User user)
    {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserAuthorities(user.getUserAuthorities());
    }

}
