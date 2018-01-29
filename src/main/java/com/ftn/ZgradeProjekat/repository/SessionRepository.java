package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Momir on 28.11.2017.
 */

/**
 * Spring Data JPA repository for the Session entity.
 */

public interface SessionRepository extends JpaRepository<Session, Integer>{

    Session findById(Long id);

    @Modifying
    @Transactional
    @Query("Update Session session SET session.record=:record WHERE session.id=:id")
    int updateRecord(@Param("id") Long id, @Param("record") String record);
}
