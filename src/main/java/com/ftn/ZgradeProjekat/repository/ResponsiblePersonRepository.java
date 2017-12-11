package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.ResponsiblePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * Created by djuro on 11/19/2017.
 */

/**
 * Spring Data JPA repository for the ResponsiblePerson entity.
 */

public interface ResponsiblePersonRepository extends JpaRepository<ResponsiblePerson,Long>
{
    @Query("SELECT rp FROM ResponsiblePerson rp WHERE " +
            "rp.tenant.id = :user_id OR rp.institution.id = :user_id")
    Set<ResponsiblePerson> findAllResponsiblePeopleByUserId(@Param("user_id") Integer userId);
}
