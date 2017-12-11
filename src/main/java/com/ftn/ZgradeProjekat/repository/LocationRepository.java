package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Building;
import com.ftn.ZgradeProjekat.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 11/19/2017.
 */

/**
 * Spring Data JPA repository for the Location entity.
 */


public interface LocationRepository extends JpaRepository<Location,Long>
{
    Location findById(Long id);
}
