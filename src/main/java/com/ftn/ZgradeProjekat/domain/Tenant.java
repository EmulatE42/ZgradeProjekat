package com.ftn.ZgradeProjekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.ZgradeProjekat.domain.DTO.RegisterUserDTO;
import com.ftn.ZgradeProjekat.domain.DTO.TenantDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Momir on 14.11.2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tenant")
public class Tenant extends User {

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    //@ManyToOne
    @ManyToMany
    @JsonIgnore
    private Set<Apartment> apartments;

    @Column(name = "tenant_is_building_manager")
    private Boolean isBuildingmManager;

    @Column(name = "tenant_is_responsible")
    private Boolean isResponsible;

    public Tenant(User user)
    {
        this.setId(user.getId());  //--------------
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserAuthorities(user.getUserAuthorities());
        this.setIsResponsible(false);
    }

    public Tenant(RegisterUserDTO registerUserDTO)
    {
        this.setUsername(registerUserDTO.getUsername());
        this.setPassword(registerUserDTO.getPassword());
        this.setFirstname(registerUserDTO.getFirstname());
        this.setLastname(registerUserDTO.getLastname());
        this.setIsBuildingmManager(false);
        this.setIsResponsible(false);
    }

    public Tenant(TenantDTO tenantDTO)
    {
        this.lastname = tenantDTO.getLastname();
        this.firstname = tenantDTO.getFirstname();
        this.setUsername(tenantDTO.getUsername());
        this.setPassword(tenantDTO.getPassword());
        this.setIsResponsible(false);
    }

    public void addApartment(Apartment apartment)
    {
        this.apartments.add(apartment);
    }

    public void removeApartment(Apartment apartment)
    {
        this.apartments.remove(apartment);
    }

}
