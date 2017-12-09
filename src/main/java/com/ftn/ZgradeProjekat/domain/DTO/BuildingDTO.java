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
    private TenantDTO buildingManager;
    private ParliamentDTO parliamentDTO;

    public BuildingDTO(Building building)
    {
        this.id = building.getId();
        this.dateOfConstruction = building.getDateOfConstruction();
        this.adress = building.getAddress();
        this.locations = new HashSet<>();
        this.parliamentDTO = new ParliamentDTO(building);
        if(building.getLocations() != null)
        {
            for(Location location : building.getLocations())
            {
                LocationDTO locationDTO = new LocationDTO(location);
                this.locations.add(locationDTO);

            }
        }

        responsiblePersonDTOs = new HashSet<>();
        if(building.getResponsiblePersons() != null) {
            for (ResponsiblePerson responsiblePerson : building.getResponsiblePersons()) {
                this.responsiblePersonDTOs.add(new ResponsiblePersonDTO(responsiblePerson));
            }
        }
        if(building.getBuildingManager()!=null) this.buildingManager = new TenantDTO((Tenant) building.getBuildingManager());
    }


}
