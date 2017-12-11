package com.ftn.ZgradeProjekat.constants;

import com.ftn.ZgradeProjekat.domain.Address;
import com.ftn.ZgradeProjekat.domain.Building;
import com.ftn.ZgradeProjekat.domain.DTO.BuildingDTO;
import com.ftn.ZgradeProjekat.domain.DTO.LocationDTO;
import com.ftn.ZgradeProjekat.domain.DTO.ResponsiblePersonDTO;
import com.ftn.ZgradeProjekat.domain.DTO.TenantDTO;

import java.util.Date;

/**
 * Created by djuro on 12/9/2017.
 */
public class BuildingConstants
{
    public static final Date DATE = new Date(2017,8,8);
    public static final String CITY = "Novi Sad";
    public static final String NUMBER = "30";
    public static final String STREET = "Bulevar Oslobodjenja";
    public static final String POSTAL_CODE = "21000";
    public static final String COUNTRY = "Serbia";
    public static final BuildingDTO BUILDING_DTO = new BuildingDTO(null, DATE,
                new Address(null,"Novi Sad","30","Bulevar Oslobodjenja",
                                    "21000","Serbia"), null,
            null,null,null);

    public static final Long BUILDING_ID = -1L;

    public static final String STREET_1 = "Brace Ribnikara";

    public static final Integer NUMBER_OF_BUILDINGS = 2;

    public static final Long LOCATION_FLOOR = 1L;

    public static final String LOCATION_TYPE = "APARTMENT";

    public static final LocationDTO LOCATION_DTO= new LocationDTO(null, "APARTMENT",
            -1L, 1L, 88L, null, null);

    public static final TenantDTO tenantDTO = new TenantDTO(-2, "hhh","hhh","hhh","hhh",false);

    public static final ResponsiblePersonDTO RESPONSIBLE_PERSON_DTO = new ResponsiblePersonDTO(null,tenantDTO,null,true,"klima urejaji");
    public static final String DESCRIPTION = "klima urejaji";

    public static final Long RESPONSIBLE_PERSON_ID = -1L;

    public static final Integer NUMBER_OF_RESPONSIBLE_PERSONS = 0;

    public static final Integer NUMBER_OF_RESPONSIBLE_PERSONS_OF_LOCATION = 2;


}
