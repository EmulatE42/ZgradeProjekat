package com.ftn.ZgradeProjekat.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by djuro on 11/4/2017.
 */
//pravimo klase Admin, Stanara,

//Admin - lista kreiranih zgrada (on kreira registruje korisnike i kreira zgrade)
//Stanar - ime, prezime, referencu na stan


//Odgovorno lice - (moze biti nadlezna osoba iz zgrade(predsednik), ili institucija), tip kvara

//Komunalni problemi - policija, vodovod i kanalizacija, javno komunalno preduzece(smece), struja, telekom, toplana

//Firma - za svaki problem imamo odredjenu firmu, stanar prijavljuje kvar  zatim odgovorno lice iz institucije bira firmu koja obavlja poslove
    //takodje odgovorno lice moze da dodaje novu firmu i da brise postojecu


//Klasa racun- koja ce biti u kvaru (imamo flag da li je kvar placen)

//Firma - za svaki kvar je odredjena odgovorna firma koju kontaktira odgovorno lice u slucaju kvara




//Firma - da li je firma isto sto i institucija (da li moze odgovorno lice da otkloni kvar)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User
{
    //dodati sliku, email, broj telefona

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id" , unique = true, nullable = false)
    private Integer id;

    @Column(name = "username")
    private String username;

    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<UserAuthority> userAuthorities = new HashSet<UserAuthority>();

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Set<UserAuthority> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(Set<UserAuthority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
