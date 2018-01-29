package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Question;
import com.ftn.ZgradeProjekat.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by EmulatE on 09-Dec-17.
 */

/**
 * Spring Data JPA repository for the Question entity.
 */


public interface QuestionRepository extends JpaRepository<Question,Long> {

}
