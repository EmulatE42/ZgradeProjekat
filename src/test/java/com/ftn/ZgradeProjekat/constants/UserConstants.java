package com.ftn.ZgradeProjekat.constants;

import com.ftn.ZgradeProjekat.domain.Address;
import com.ftn.ZgradeProjekat.domain.DTO.RegisterUserDTO;

/**
 * Created by djuro on 12/10/2017.
 */
public class UserConstants
{
    public static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhIiwiY3JlYXRlZCI6MTUxMjkyODI1ODE2MiwiZXhwIjoxNTEyOTQ2MjU4fQ.3YV-a2oD0-VDo6WASrlQ3PwEs1GthMWC9rmnwl3IgMiSA9J_hNzURkJD_pUBq6P2XBORDSwd8Gi5Z8Y2OAox2A";

    public static final Address address = new Address(1L,"Novi Sad","30","Bulevar Oslobodjenja","21000","Serbia");
    public static final RegisterUserDTO REGISTER_USER_DTO = new RegisterUserDTO("stefan", "stefan", "TENANT", "stefan", "stefan",null,address,null,null);

    public static final String USERNAME = "stefan";

    public static final String PASSWORD = "stefan";

    public static final String FIRSTNAME = "stefan";

    public static final String LASTNAME = "stefan";

    public static final String ROLE = "TENANT";

    public static final Integer NUMBER_OF_TENANTS = 2;

    public static final Integer NUMBER_OF_INSTITUTION = 1;

    public static final Integer NUMBER_OF_TENANTS_OF_BUILDING = 0;

}
