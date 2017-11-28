package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<TenantDTO> tenantDTOs = new HashSet<>();

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
            if(((Apartment) location).getOwners()!=null)
            {
                for(User user : ((Apartment) location).getOwners())
                {
                    if(user instanceof Tenant)
                    {
                        this.tenantDTOs.add(new TenantDTO((Tenant) user));
                    }
                }
            }
            else
                this.tenantDTOs = null;
        }
        else if(location instanceof Hallway)
        {
            this.type = "HALLWAY";
            this.floor = null;
            this.square = null;
            this.numberOfFloors = ((Hallway) location).getNumberOfFloors();
            this.tenantDTOs = null;
        }
        else if(location instanceof Attic)
        {
            this.type = "ATTIC";
            this.floor = ((Attic) location).getFloor();
            this.square = ((Attic) location).getSquare();
            this.numberOfFloors = null;
            this.tenantDTOs = null;
        }
        else if(location instanceof Basement)
        {
            this.type = "BASEMENT";
            this.floor = null;
            this.square = ((Basement) location).getSquare();
            this.numberOfFloors = null;
            this.tenantDTOs = null;
        }
    }
}
