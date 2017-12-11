package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EmulatE on 09-Dec-17.
 */

/**
 * Spring Data JPA repository for the Answer entity.
 */


public interface AnswerRepository extends JpaRepository<Answer,Integer> {
}
