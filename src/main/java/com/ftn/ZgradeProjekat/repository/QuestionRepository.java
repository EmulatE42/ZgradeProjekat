package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EmulatE on 09-Dec-17.
 */

/**
 * Spring Data JPA repository for the Question entity.
 */


public interface QuestionRepository extends JpaRepository<Question,Long> {


}
