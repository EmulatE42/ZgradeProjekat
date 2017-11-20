package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.Location;

/**
 * Created by djuro on 11/19/2017.
 */
public interface LocationService
{
    Location getLocationById(Long locationId);

    Boolean deleteLocation(Long locationId);
}
