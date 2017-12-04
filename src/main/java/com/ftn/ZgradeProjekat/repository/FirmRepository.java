package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Firm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 12/3/2017.
 */
public interface FirmRepository extends JpaRepository<Firm,Integer>
{
}
