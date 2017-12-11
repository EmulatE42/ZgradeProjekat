package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Institution;
import com.ftn.ZgradeProjekat.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 11/28/2017.
 */

/**
 * Spring Data JPA repository for the Institution entity.
 */


public interface InstitutionRepository extends JpaRepository<Institution,Integer>
{
}
