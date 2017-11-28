package com.ftn.ZgradeProjekat.domain;

import com.ftn.ZgradeProjekat.domain.DTO.ResponsiblePersonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Momir on 14.11.2017.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ResponsiblePerson
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "responsible_person_id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    private Tenant tenant;

    @OneToOne
    private Institution institution;

    @Column(name = "responsible_person_is_tenant")
    private Boolean isTenant;

    @Column(name = "responsible_person_description")
    private String description;

    public ResponsiblePerson(ResponsiblePersonDTO responsiblePersonDTO, Tenant tenant)
    {
        this.tenant = tenant;
        this.institution = null;
        this.isTenant = responsiblePersonDTO.getIsTenant();
        this.description = responsiblePersonDTO.getDescription();
    }

    public ResponsiblePerson(ResponsiblePersonDTO responsiblePersonDTO, Institution institution)
    {
        this.tenant = null;
        this.institution = institution;
        this.isTenant = responsiblePersonDTO.getIsTenant();
        this.description = responsiblePersonDTO.getDescription();
    }

    /*
    public ResponsiblePerson(User user)
    {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserAuthorities(user.getUserAuthorities());
    }
    */
}
