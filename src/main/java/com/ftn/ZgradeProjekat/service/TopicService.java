package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.DTO.TopicDTO;
import com.ftn.ZgradeProjekat.domain.Topic;
import com.ftn.ZgradeProjekat.domain.Vote;

import java.util.List;

/**
 * Created by Momir on 08.12.2017.
 */
public interface TopicService {

    TopicDTO addTopic(Topic topic);
    Topic getTopic(Long id);
    boolean deleteTopic(Long id);
    List<Topic> getTopics();
    Integer updatePositiveVote(Long id);
    Integer updateNegativeVote(Long id);
}
