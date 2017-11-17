package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.Building;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by djuro on 11/17/2017.
 */

@Getter
@Setter
public class BuildingListItemDTO
{
    private Long id;
    private String city;
    private String street;
    private String number;

    public BuildingListItemDTO(Building building)
    {
        this.id = building.getId();
        this.city = building.getAddress().getCity();
        this.street = building.getAddress().getStreet();
        this.number = building.getAddress().getNumber();
    }
}
