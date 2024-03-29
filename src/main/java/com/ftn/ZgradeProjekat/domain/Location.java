package com.ftn.ZgradeProjekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


/**
 * Created by djuro on 11/4/2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Table(name = "location")
public abstract class Location
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "location_id")
    protected Long id;

    @OneToMany
    protected Set<Bug> bugs; //brisanjem buga brise se ovde

    @JsonIgnore
    @ManyToOne
    protected Building building;

    public void addBug(Bug bug)
    {
        this.bugs.add(bug);
    }

    public void removeBug(Bug bug)
    {
        this.bugs.remove(bug);
    }
}
