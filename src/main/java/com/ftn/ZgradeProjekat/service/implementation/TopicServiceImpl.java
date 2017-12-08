package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Topic;
import com.ftn.ZgradeProjekat.repository.TopicRepository;
import com.ftn.ZgradeProjekat.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Momir on 08.12.2017.
 */
@Service
public class TopicServiceImpl implements TopicService{

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Topic addTopic(Topic topic) {

        Topic saved = this.topicRepository.save(topic);
        return saved;
    }

    @Override
    public Topic getTopic(Long id) {

        Topic topic = this.topicRepository.findById(id);
        return topic;
    }

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

    @Override
    public List<Topic> getTopics() {

        List<Topic> topics = this.topicRepository.findAll();
        return topics;
    }
}
