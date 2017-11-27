package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by djuro on 11/17/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO
{
    private Long locationId;
    private String type; //apartment, hallway...
    private Long buildingId;
    private Long floor;
    private Long square;
    private Long numberOfFloors;
    private TenantDTO tenantDTO;

    /*
    public LocationDTO(Long id, String type, Long buildingId, Long floor, Long square, Long numberOfFloors)
    {
        this.locationId = id;
        this.type = type;
        this.buildingId = buildingId;
        this.floor = floor;
        this.square = square;
        this.numberOfFloors = numberOfFloors;
    }
    */

    public LocationDTO(Location location)
    {
        this.locationId = location.getId();
        this.buildingId = location.getBuilding().getId();
        if(location instanceof Apartment)
        {
            this.type = "APARTMENT";
            this.floor = ((Apartment) location).getFloor();
            this.square = ((Apartment) location).getSquare();
            this.numberOfFloors = null;
            if(((Apartment) location).getOwner()!=null)
                this.tenantDTO = new TenantDTO((Tenant)((Apartment) location).getOwner());
            else
                this.tenantDTO = null;
        }
        else if(location instanceof Hallway)
        {
            this.type = "HALLWAY";
            this.floor = null;
            this.square = null;
            this.numberOfFloors = ((Hallway) location).getNumberOfFloors();
            this.tenantDTO = null;
        }
        else if(location instanceof Attic)
        {
            this.type = "ATTIC";
            this.floor = ((Attic) location).getFloor();
            this.square = ((Attic) location).getSquare();
            this.numberOfFloors = null;
            this.tenantDTO = null;
        }
        else if(location instanceof Basement)
        {
            this.type = "BASEMENT";
            this.floor = null;
            this.square = ((Basement) location).getSquare();
            this.numberOfFloors = null;
            this.tenantDTO = null;
        }
    }
}
