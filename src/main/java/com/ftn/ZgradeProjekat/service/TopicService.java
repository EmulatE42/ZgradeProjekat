package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.Topic;

import java.util.List;

/**
 * Created by Momir on 08.12.2017.
 */
public interface TopicService {

    Topic addTopic(Topic topic);
    Topic getTopic(Long id);
    boolean deleteTopic(Long id);
    List<Topic> getTopics();
}
