package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Momir on 08.12.2017.
 */
public interface TopicRepository extends JpaRepository<Topic, Integer> {

    Topic findById(Long id);
}
