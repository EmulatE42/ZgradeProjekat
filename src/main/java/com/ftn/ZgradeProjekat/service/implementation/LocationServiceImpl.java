package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Location;
import com.ftn.ZgradeProjekat.repository.LocationRepository;
import com.ftn.ZgradeProjekat.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by djuro on 11/19/2017.
 */
@Service
public class LocationServiceImpl implements LocationService
{
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository)
    {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location getLocationById(Long locationId)
    {
        Location location = locationRepository.getOne(locationId);
        return location;
    }

    @Override
    public Boolean deleteLocation(Long locationId)
    {
        Location locationForDelete = null;
        Boolean deleted = false;
        locationForDelete = locationRepository.getOne(locationId);
        if(locationForDelete != null)
        {
            locationRepository.delete(locationForDelete);
            deleted = true;
        }

        return deleted;
    }
}
