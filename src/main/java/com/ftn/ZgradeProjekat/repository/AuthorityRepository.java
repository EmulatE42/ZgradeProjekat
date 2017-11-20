package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Attic;
import com.ftn.ZgradeProjekat.domain.Authority;
import com.ftn.ZgradeProjekat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by djuro on 11/19/2017.
 */
public interface AuthorityRepository extends JpaRepository<Authority,Long>
{
    @Query("SELECT w FROM Authority w WHERE w.name = :name")
    Authority findByName(@Param("name") String name);
}
