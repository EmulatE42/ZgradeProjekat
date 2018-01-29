package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by EmulatE on 09-Dec-17.
 */

/**
 * Spring Data JPA repository for the Answer entity.
 */


public interface AnswerRepository extends JpaRepository<Answer,Long> {

    @Modifying
    @Transactional
    @Query("Update Answer SET a1=:a WHERE answer_id=:id")
    void updateAnswer(@Param("a") Long a,@Param("id") Long id);
}
// update answer set a1=? where answer_id=?