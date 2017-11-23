package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.DTO.LocationDTO;

/**
 * Created by djuro on 11/19/2017.
 */
public interface LocationService
{
    LocationDTO getLocationById(Long locationId);

    Boolean deleteLocation(Long locationId);

    Boolean connectTenantAndApartment(Long apartmentId, Integer tenantId);
}
