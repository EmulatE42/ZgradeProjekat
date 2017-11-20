package com.ftn.ZgradeProjekat.domain;

import com.ftn.ZgradeProjekat.domain.DTO.BuildingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by djuro on 11/4/2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "building")
public class Building
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "building_id", unique = true, nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "building_date_of_construction")
    private Date dateOfConstruction;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "building_user_id")
    private User menager;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Location> locations;

    @OneToMany
    private Set<Notification> notifications;

    @OneToMany
    private Set<Survey> surveys;

    @OneToOne
    @JoinColumn(referencedColumnName = "parlament_id", name = "building_parlament_id")
    private Parlament parlament;

    public void addLocation(Location location)
    {
        locations.add(location);

    }

    public Building(BuildingDTO buildingDTO)
    {
        this.dateOfConstruction = buildingDTO.getDateOfConstruction();
        this.address = buildingDTO.getAdress();
    }

}
