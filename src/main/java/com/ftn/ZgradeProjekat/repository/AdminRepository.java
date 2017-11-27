package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 11/19/2017.
 */
public interface AdminRepository extends JpaRepository<Admin,Long>
{
}
