package com.ftn.ZgradeProjekat.domain;

import com.ftn.ZgradeProjekat.domain.DTO.RegisterUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    //private String konacnomenanje;

    public Institution(User user)
    {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserAuthorities(user.getUserAuthorities());
    }

    public Institution(RegisterUserDTO registerUserDTO)
    {
        this.setUsername(registerUserDTO.getUsername());
        this.setPassword(registerUserDTO.getPassword());
        this.setName(registerUserDTO.getInstitutionName());
        this.setAddress(registerUserDTO.getAddress());
    }

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
