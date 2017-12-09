package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.Building;
import com.ftn.ZgradeProjekat.domain.Parliament;
import com.ftn.ZgradeProjekat.domain.Session;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Momir on 01.12.2017.
 */
@Getter
@Setter
public class ParliamentDTO {

    private Long id;
    private Set<SessionDTO> sessions;
    private AddressDTO buildingAddress;

    public ParliamentDTO()
    {
        this.sessions = new HashSet<>();
    }

    public ParliamentDTO(Building building)
    {
        this();
        if(building.getParliament() != null)
        {
            this.id = building.getParliament().getId();
            for(Session session: building.getParliament().getSessions())
            {
                this.sessions.add(new SessionDTO(session));
            }
        }
        this.buildingAddress = new AddressDTO(building.getAddress());

    }
}
