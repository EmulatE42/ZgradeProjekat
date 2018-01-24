package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Momir on 08.12.2017.
 */

/**
 * Spring Data JPA repository for the Topic entity.
 */
public interface TopicRepository extends JpaRepository<Topic, Integer> {

    Topic findById(Long id);

    @Modifying
    @Transactional
    @Query("Update Topic topic SET topic.pos_votes = topic.pos_votes + 1 WHERE topic.id=:id")
    Integer updatePositiveVote(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("Update Topic topic SET topic.neg_votes = topic.neg_votes + 1 WHERE topic.id=:id")
    Integer updateNegativeVote(@Param("id") Long id);

}
