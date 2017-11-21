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
    private String type; //apartmant,hallway...
    private Long buildingId;
    private Long flor;
    private Long square;
    private Long numberOfFlors;
    private TenantDTO tenantDTO;

    /*
    public LocationDTO(Long id, String type, Long buildingId, Long flor, Long square, Long numberOfFlors)
    {
        this.locationId = id;
        this.type = type;
        this.buildingId = buildingId;
        this.flor = flor;
        this.square = square;
        this.numberOfFlors = numberOfFlors;
    }
    */

    public LocationDTO(Location location)
    {
        this.locationId = location.getId();
        this.buildingId = location.getBuilding().getId();
        if(location instanceof Apartment)
        {
            this.type = "APARTMENT";
            this.flor = ((Apartment) location).getFlor();
            this.square = ((Apartment) location).getSquare();
            this.numberOfFlors = null;
            if(((Apartment) location).getOwner()!=null)
                this.tenantDTO = new TenantDTO((Tenant)((Apartment) location).getOwner());
            else
                this.tenantDTO = null;
        }
        else if(location instanceof Hallway)
        {
            this.type = "HALLWAY";
            this.flor = null;
            this.square = null;
            this.numberOfFlors = ((Hallway) location).getNumberOfFlors();
            this.tenantDTO = null;
        }
        else if(location instanceof Attic)
        {
            this.type = "ATTIC";
            this.flor = ((Attic) location).getFlor();
            this.square = ((Attic) location).getSquare();
            this.numberOfFlors = null;
            this.tenantDTO = null;
        }
        else if(location instanceof Basement)
        {
            this.type = "BASEMENT";
            this.flor = null;
            this.square = ((Basement) location).getSquare();
            this.numberOfFlors = null;
            this.tenantDTO = null;
        }
    }
}
