package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 11/29/2017.
 */
public interface BugRepository extends JpaRepository<Bug,Long>
{
}
