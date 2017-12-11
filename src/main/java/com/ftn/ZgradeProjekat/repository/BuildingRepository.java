package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Building;
import com.ftn.ZgradeProjekat.domain.DTO.BuildingListItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by djuro on 11/17/2017.
 */

/**
 * Spring Data JPA repository for the Building entity.
 */


public interface BuildingRepository extends JpaRepository<Building,Long>
{
    Building findById(Long id);
}
