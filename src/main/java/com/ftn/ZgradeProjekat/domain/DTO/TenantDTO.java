package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.Tenant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by djuro on 11/21/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenantDTO
{
    private Integer id;
    private String username;
    private String firstname;
    private String lastname;
    private Boolean isBuildingManager;

    public TenantDTO(Tenant tenant)
    {
        this.id = tenant.getId();
        this.username = tenant.getUsername();
        this.firstname = tenant.getFirstname();
        this.lastname = tenant.getLastname();
        this.isBuildingManager = tenant.getIsBuildingmManager();
    }

}
