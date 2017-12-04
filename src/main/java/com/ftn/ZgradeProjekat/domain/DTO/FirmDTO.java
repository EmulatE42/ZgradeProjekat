package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.Address;
import com.ftn.ZgradeProjekat.domain.Firm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by djuro on 12/3/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FirmDTO
{
    private Integer id;
    private String username;
    private Address address;
    private String name;
    private String description;

    public FirmDTO(Firm firm)
    {
        this.id = firm.getId();
        this.username = firm.getUsername();
        this.address = firm.getAddress();
        this.name = firm.getName();
        this.description = firm.getDescription();
    }
}
