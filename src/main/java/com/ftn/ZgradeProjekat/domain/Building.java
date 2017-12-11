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

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Location> locations;

    @OneToMany
    private Set<Notification> notifications;

    @OneToMany
    private Set<Survey> surveys;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(referencedColumnName = "parliament_id", name = "building_parliament_id")
    private Parliament parliament;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<ResponsiblePerson> responsiblePersons;  //brisanjem responsible persona brise se i ovde

    @OneToOne
    private User buildingManager;

    public void addLocation(Location location)
    {
        locations.add(location);
    }

    public Building(BuildingDTO buildingDTO)
    {
        this.dateOfConstruction = buildingDTO.getDateOfConstruction();
        this.address = buildingDTO.getAdress();
    }

    public void addResponsiblePerson(ResponsiblePerson responsiblePerson)
    {
        this.responsiblePersons.add(responsiblePerson);
    }

    public void removeResponsiblePerson(ResponsiblePerson responsiblePerson)
    {
        this.responsiblePersons.remove(responsiblePerson);
    }

    public void removeLocation(Location location)
    {
        this.locations.remove(location);
    }

}
