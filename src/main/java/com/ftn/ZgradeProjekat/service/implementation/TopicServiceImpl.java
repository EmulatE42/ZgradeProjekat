package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.DTO.TopicDTO;
import com.ftn.ZgradeProjekat.domain.Topic;
import com.ftn.ZgradeProjekat.domain.Vote;
import com.ftn.ZgradeProjekat.repository.TopicRepository;
import com.ftn.ZgradeProjekat.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Momir on 08.12.2017.
 */

/**
 * Implementation service for managing Topic.
 */

@Service
public class TopicServiceImpl implements TopicService{

    @Autowired
    private TopicRepository topicRepository;

    /**
     * Save a topic.
     *
     * @param topic the entity to add to database
     * @return the persisted entity
     */
    @Override
    public TopicDTO addTopic(Topic topic) {

        Topic saved = this.topicRepository.save(topic);

        TopicDTO topicDTO = new TopicDTO(saved);
        return topicDTO;
    }

    /**
     * Return one a topic by id.
     *
     * @param id -
     * @return topic
     */
    @Override
    public Topic getTopic(Long id) {

        Topic topic = this.topicRepository.findById(id);
        return topic;
    }

    /**
     * Delete one a topic by id.
     *
     * @param id -
     * @return true if topic is being deleted or false
     */
    @Override
    public boolean deleteTopic(Long id) {

        Topic topic = this.topicRepository.findById(id);

        if(topic != null)
        {
            this.topicRepository.delete(topic);
            return true;
        }
        return false;
    }

    /**
     * Return list of topics.
     *
     * @return topics
     */
    @Override
    public List<Topic> getTopics() {

        List<Topic> topics = this.topicRepository.findAll();
        return topics;
    }

    /**
     * Update positive vote
     *
     * @return Integer
     */

    @Override
    public Integer updatePositiveVote(Long id) {
        return this.topicRepository.updatePositiveVote(id);
    }

    /**
     * Update negative vote
     *
     * @return Integer
     */

    @Override
    public Integer updateNegativeVote(Long id) {
        return this.topicRepository.updateNegativeVote(id);
    }

}
