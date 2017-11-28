package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.Address;
import com.ftn.ZgradeProjekat.domain.Institution;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by djuro on 11/28/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionDTO
{
    private Integer id;
    private String username;
    private Address address;
    private String name;

    public InstitutionDTO(Institution institution)
    {
        this.id = institution.getId();
        this.username = institution.getUsername();
        this.address = institution.getAddress();
        this.name = institution.getName();
    }
}
