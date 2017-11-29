package com.ftn.ZgradeProjekat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by EmulatE on 26-Nov-17.
 */

@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "institution")

public class Institution extends User {

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "institution_name")
    private String name;
    private String konacnomenanje;
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;


    @OneToOne(cascade = CascadeType.ALL)
    private ResponsiblePerson responsiblePerson;

    */
}
