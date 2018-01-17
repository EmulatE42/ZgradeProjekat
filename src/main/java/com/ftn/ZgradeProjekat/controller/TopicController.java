package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.DTO.TopicDTO;
import com.ftn.ZgradeProjekat.domain.DTO.VoteDTO;
import com.ftn.ZgradeProjekat.domain.Session;
import com.ftn.ZgradeProjekat.domain.Tenant;
import com.ftn.ZgradeProjekat.domain.Topic;
import com.ftn.ZgradeProjekat.domain.Vote;
import com.ftn.ZgradeProjekat.repository.SessionRepository;
import com.ftn.ZgradeProjekat.repository.TenantRepository;
import com.ftn.ZgradeProjekat.service.SessionService;
import com.ftn.ZgradeProjekat.service.TopicService;
import com.ftn.ZgradeProjekat.service.VoteService;
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

    @Autowired
    private VoteService voteService;

    @Autowired
    private TenantRepository tenantRepository;

    /**
     * POST  /add : Create a new topic.
     *
     * @param topic -
     * @return the ResponseEntity with status 201 (Created) and with body the new topic, or with status 409 (Conflict) if the topic has already an ID
     */

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicDTO> addTopic(@RequestBody Topic topic)
    {
        TopicDTO saved = topicService.addTopic(topic);

        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * POST  /addBySessionId/{sessionId} : Create a new topic which contains a session with sessionID
     *
     * @param sessionId,topicDTO -
     * @return the ResponseEntity with status 201 (Created) and with body the new topic, or with status 404 (Not Found) if the session with sessionID doesn't exist
     */

    @RequestMapping(value = "/addBySessionId/{sessionId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicDTO> addTopicBySessionId(@PathVariable("sessionId") Long sessionId, @RequestBody TopicDTO topicDTO)
    {
        Session session = this.sessionService.getSession(sessionId);

        if(session == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            Topic topic = new Topic(topicDTO, session);
            TopicDTO saved = this.topicService.addTopic(topic);

//            session.getTopics().add(topic);
//            this.sessionService.addSession(session);

            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
    }

    /**
     * GET  /getById/{topicId} : Returning a topic which has a topicId
     *
     * @param topicId -
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
     * @param topicId -
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
        List<Topic> topics = this.topicService.getTopics();

        if(topics == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            Set<TopicDTO> topicsDTO = new HashSet<>();

            for(Topic topic: topics) {

                if(topic.getSession().getId() == sessionId)
                    topicsDTO.add(new TopicDTO(topic));
            }

            return new ResponseEntity<>(topicsDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/positiveVote/{tenantId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicDTO> positiveVote(@PathVariable("tenantId") Integer tenantId, @RequestBody TopicDTO topicDTO)
    {
        Integer updated = this.topicService.updatePositiveVote(topicDTO.getId());

        if(updated != 1)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {

            Tenant tenant = this.tenantRepository.findById(tenantId);
            Topic topic = new Topic(topicDTO, null);

            voteService.addVote(new Vote(null, tenant, topic));
            return new ResponseEntity<>(topicDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/negativeVote/{tenantId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicDTO> negativeVote(@PathVariable("tenantId") Integer tenantId, @RequestBody TopicDTO topicDTO)
    {
        Integer updated = this.topicService.updateNegativeVote(topicDTO.getId());

        if(updated != 1)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {

            Tenant tenant = this.tenantRepository.findById(tenantId);
            Topic topic = new Topic(topicDTO, null);

            voteService.addVote(new Vote(null, tenant, topic));
            return new ResponseEntity<>(topicDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getVote/{tenantId}/{topicID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteDTO> getVote(@PathVariable("tenantId") Integer tenantId, @PathVariable("topicID") Long topicID)
    {
        VoteDTO vote = this.voteService.getVote(topicID, tenantId);

        if(vote == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(vote, HttpStatus.OK);
        }
    }
}
