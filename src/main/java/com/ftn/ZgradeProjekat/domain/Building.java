package com.ftn.ZgradeProjekat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by djuro on 11/4/2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
@Table(name = "building")
public class Building
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restaurant_id", unique = true, nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "building_date_of_construction")
    private Long dateOfConstruction;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "building_user_id")
    private User menager;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Location> locations;

    @OneToMany
    private Set<Notification> notifications;

    @OneToMany
    private Set<Survey> surveys;

    @OneToMany
    private Set<Parlament> parlaments;
}
