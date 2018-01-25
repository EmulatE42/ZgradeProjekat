package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Momir on 16.01.2018.
 */
public interface VoteRepository extends JpaRepository<Vote, Integer> {

        Vote findById(Long id);

        @Query("SELECT vote FROM Vote vote WHERE vote.topic.id = :topic_id and vote.tenant.id = :tenant_id")
        Vote getVote( @Param("topic_id") Long topic_id, @Param("tenant_id") Integer tenant_id);

}
