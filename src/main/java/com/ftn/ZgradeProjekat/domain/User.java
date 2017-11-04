package com.ftn.ZgradeProjekat.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by djuro on 11/4/2017.
 */
@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
