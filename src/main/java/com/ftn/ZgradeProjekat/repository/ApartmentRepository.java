package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 11/17/2017.
 */
public interface ApartmentRepository extends JpaRepository<Apartment,Long>
{
}
