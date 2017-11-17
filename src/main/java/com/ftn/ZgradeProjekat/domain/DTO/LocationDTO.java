package com.ftn.ZgradeProjekat.domain.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by djuro on 11/17/2017.
 */
@Getter
@Setter
public class LocationDTO
{
    private Long locationId;
    private String type; //apartmant,hallway...
    private Long buildingId;
    private Long flor;
    private Long square;
    private Long numberOfFlors;
}
