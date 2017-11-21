package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Tenant;
import com.ftn.ZgradeProjekat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 11/21/2017.
 */
public interface TenantRepository extends JpaRepository<Tenant,Integer>
{
    Tenant findById(Integer id);
}
