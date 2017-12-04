package com.ftn.ZgradeProjekat.domain.DTO;


import com.ftn.ZgradeProjekat.domain.Address;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Momir on 03.12.2017.
 */
@Getter
@Setter
public class AddressDTO {

    private Long id;
    private String number;
    private String street;
    private String postalCode;
    private String country;

    public AddressDTO () {}

    public AddressDTO(Address address)
    {
        this.id = address.getId();
        this.number = address.getNumber();
        this.street = address.getStreet();
        this.postalCode = address.getPostalCode();
        this.country = address.getCountry();
    }
}
