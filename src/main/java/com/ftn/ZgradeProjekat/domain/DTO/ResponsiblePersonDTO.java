package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.ResponsiblePerson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by djuro on 11/28/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsiblePersonDTO
{
    private Long id;
    private TenantDTO tenantDTO;
    private InstitutionDTO institutionDTO;
    private Boolean isTenant;
    private String description;

    public ResponsiblePersonDTO(ResponsiblePerson responsiblePerson)
    {
        this.id = responsiblePerson.getId();
        if(responsiblePerson.getIsTenant())
        {
            this.tenantDTO = new TenantDTO(responsiblePerson.getTenant());
            this.institutionDTO = null;
        }
        else
        {
            this.tenantDTO = null;
            this.institutionDTO = new InstitutionDTO(responsiblePerson.getInstitution());
        }
        this.isTenant = responsiblePerson.getIsTenant();
        this.description = responsiblePerson.getDescription();
    }

}
