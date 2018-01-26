package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by EmulatE on 09-Dec-17.
 */

/**
 * Spring Data JPA repository for the Survey entity.
 */


public interface SurveyRepository extends JpaRepository<Survey,Long> {
    @Query("SELECT MAX(s.id) FROM Survey s")
    Long getMaxId();
}
