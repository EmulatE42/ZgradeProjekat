package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.DTO.TopicDTO;
import com.ftn.ZgradeProjekat.domain.Session;
import com.ftn.ZgradeProjekat.domain.Topic;
import com.ftn.ZgradeProjekat.repository.SessionRepository;
import com.ftn.ZgradeProjekat.service.SessionService;
import com.ftn.ZgradeProjekat.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Momir on 08.12.2017.
 */

/**
 * REST controller for managing Topic
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private SessionService sessionService;

    /**
     * POST  /add : Create a new topic.
     *
     * @param topic
     * @return the ResponseEntity with status 201 (Created) and with body the new topic, or with status 409 (Conflict) if the topic has already an ID
     */

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> addTopic(@RequestBody Topic topic)
    {
        Topic saved = topicService.addTopic(topic);

        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * POST  /addBySessionId/{sessionId} : Create a new topic which contains a session with sessionID
     *
     * @param sessionId,topicDTO
     * @return the ResponseEntity with status 201 (Created) and with body the new topic, or with status 404 (Not Found) if the session with sessionID doesn't exist
     */

    @RequestMapping(value = "/addBySessionId/{sessionId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> addTopicBySessionId(@PathVariable("sessionId") Long sessionId, @RequestBody TopicDTO topicDTO)
    {
        Session session = this.sessionService.getSession(sessionId);

        if(session == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            Topic topic = new Topic(topicDTO);
            session.getTopics().add(topic);
            this.sessionService.addSession(session);

            return new ResponseEntity<>(topic, HttpStatus.CREATED);
        }
    }

    /**
     * GET  /getById/{topicId} : Returning a topic which has a topicId
     *
     * @param topicId
     * @return the ResponseEntity with status 200 (OK) and with body the new topic, or with status 404 (Not Found) if the topic hasn't a topicId
     */

    @RequestMapping(value = "/getById/{topicId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> getById(@PathVariable("topicId") Long topicId)
    {
        Topic topic = this.topicService.getTopic(topicId);

        if(topic == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    /**
     * GET  /deleteTopic/{topicId} : Deleting a topic which has a topicId
     *
     * @param topicId
     * @return the ResponseEntity with status 200 (OK) and with body the new topic, or with status 404 (Not Found) if the topic hasn't a topicId
     */

    @RequestMapping(value = "/deleteTopic/{topicId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteTopic(@PathVariable("topicId") Long topicId)
    {
        Boolean deleted = this.topicService.deleteTopic(topicId);

        if(deleted == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    /**
     * GET  /getTopics : Returning a list of topics
     *
     * @return the ResponseEntity with status 200 (OK) and with body the new topics, or with status 404 (Not Found) if the topics doesn't exist
     */

    @RequestMapping(value = "/getTopics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Topic>> getTopics()
    {
        List<Topic> topics = this.topicService.getTopics();

        if(topics == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    /**
     * GET  /getTopicsBySessionId/{sessionId} : Returning a list of topics which contain a session with a sessionID
     *
     * @return the ResponseEntity with status 200 (OK) and with body the new topics, or with status 404 (Not Found) if the session doesn't exist with sessionID
     */

    @RequestMapping(value = "/getTopicsBySessionId/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<TopicDTO>> getTopicsBySessionId(@PathVariable("sessionId") Long sessionId)
    {
        Session session = this.sessionService.getSession(sessionId);

        if(session == null) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            Set<TopicDTO> topics = new HashSet<>();

            for(Topic topic: session.getTopics())
                topics.add(new TopicDTO(topic));

            return new ResponseEntity<>(topics, HttpStatus.OK);
        }
    }
}
