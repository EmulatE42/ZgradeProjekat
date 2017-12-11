package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Basement;
import com.ftn.ZgradeProjekat.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 12/3/2017.
 */

/**
 * Spring Data JPA repository for the Bill entity.
 */


public interface BillRepository extends JpaRepository<Bill,Long>
{
}
