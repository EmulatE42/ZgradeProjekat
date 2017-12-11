package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EmulatE on 09-Dec-17.
 */

/**
 * Spring Data JPA repository for the Survey entity.
 */


public interface SurveyRepository extends JpaRepository<Survey,Long> {

}
