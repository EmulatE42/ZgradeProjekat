package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by djuro on 11/20/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDTO
{
    private Long id;
    private Date dateOfConstruction;
    private Address adress;
    /*
    private String city;
    private String street;
    private String number;
    private String postalCode;
    private String country;
    */
}
