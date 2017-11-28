package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Set<LocationDTO> locations;
    private Set<ResponsiblePersonDTO> responsiblePersonDTOs;

    public BuildingDTO(Building building)
    {
        this.id = building.getId();
        this.dateOfConstruction = building.getDateOfConstruction();
        this.adress = building.getAddress();
        this.locations = new HashSet<>();
        for(Location location : building.getLocations())
        {
            LocationDTO locationDTO = new LocationDTO(location);
            this.locations.add(locationDTO);

            /*
            if(location instanceof Apartment)
            {
                LocationDTO locationDTO = new LocationDTO(location.getId(),"APARTMENT",location.getBuilding().getId(),
                        ((Apartment) location).getFloor(),((Apartment) location).getSquare(),null);
                this.locations.add(locationDTO);
            }
            else if(location instanceof Hallway)
            {
                this.locations.add(new LocationDTO(location.getId(),"HALLWAY",location.getBuilding().getId(),
                        null,null,((Hallway) location).getNumberOfFloors()));
            }
            else if(location instanceof Attic)
            {
                this.locations.add(new LocationDTO(location.getId(),"ATTIC",location.getBuilding().getId(),
                        ((Attic) location).getFloor(),((Attic) location).getSquare(),null));
            }
            else if(location instanceof Basement)
            {
                this.locations.add(new LocationDTO(location.getId(),"BASEMENT",location.getBuilding().getId(),
                        null,((Basement) location).getSquare(),null));
            }
            */
        }

        responsiblePersonDTOs = new HashSet<>();
        for(ResponsiblePerson responsiblePerson : building.getResponsiblePersons())
        {
            this.responsiblePersonDTOs.add(new ResponsiblePersonDTO(responsiblePerson));
        }
    }


}
