package com.ftn.ZgradeProjekat.web.controller;

import com.ftn.ZgradeProjekat.TestUtil;
import com.ftn.ZgradeProjekat.ZgradeProjekatApplication;
import com.ftn.ZgradeProjekat.constants.ParliamentConstants;
import com.ftn.ZgradeProjekat.constants.SessionConstants;
import com.ftn.ZgradeProjekat.constants.TopicConstants;
import com.ftn.ZgradeProjekat.domain.DTO.LoginRequestDTO;
import com.ftn.ZgradeProjekat.domain.DTO.LoginResponseDTO;
import com.ftn.ZgradeProjekat.domain.Parliament;
import com.ftn.ZgradeProjekat.domain.Session;
import com.ftn.ZgradeProjekat.domain.Topic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Momir on 10.12.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations="classpath:test.properties")
public class TopicControllerTest {

    private static final String URL_PREFIX = "/topic";

    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String loginToken;

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.
                webAppContextSetup(webApplicationContext).build();

        ResponseEntity<LoginResponseDTO> responseEntity = testRestTemplate.postForEntity("/api/login",
                new LoginRequestDTO("aaa", "aaa"), LoginResponseDTO.class);
        loginToken = responseEntity.getBody().getToken();
        System.out.println(loginToken);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void addTopic() throws Exception {
        Topic topic = new Topic();
        topic.setId(TopicConstants.DB_NEW_ID);
        topic.setDescription(TopicConstants.DB_NEW_DESCRIPTION);
        topic.setAccepted(TopicConstants.DB_NEW_ACCEPTED);
        topic.setVotes(TopicConstants.DB_NEW_VOTES);



        String json = TestUtil.json(topic);
        this.mockMvc.perform(post(URL_PREFIX + "/add")
                .contentType(contentType)
                .header("X-Auth-Token", loginToken)
                .content(json))
                .andExpect(status().isCreated());
    }


    @Test
    public void testGetAllTopics() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getTopics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(TopicConstants.DB_COUNT_TOPICS)));
    }

//    @Test
//    public void testGetTopic() throws Exception {
//        mockMvc.perform(get(URL_PREFIX + "/getById/" + TopicConstants.DB_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(contentType))
//                .andExpect(jsonPath("$.id").value(
//                        hasItem(TopicConstants.DB_ID.intValue())))
//                .andExpect(jsonPath("$.description").value(
//                        hasItem(TopicConstants.DB_DESCRIPTION)))
//                .andExpect(jsonPath("$.accepted").value(
//                        hasItem(TopicConstants.DB_ACCEPTED)))
//                .andExpect(jsonPath("$.votes").value(
//                        hasItem(TopicConstants.DB_VOTES)));
//    }


    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteTopic() throws Exception {
        this.mockMvc.perform(get(URL_PREFIX + "/deleteTopic/" + TopicConstants.DB_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTopics() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getTopics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(TopicConstants.DB_COUNT_TOPICS)));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddTopicBySessionId() throws Exception {

        Topic topic = new Topic();
        topic.setId(TopicConstants.DB_NEW_ID);
        topic.setDescription(TopicConstants.DB_NEW_DESCRIPTION);
        topic.setAccepted(TopicConstants.DB_NEW_ACCEPTED);
        topic.setVotes(TopicConstants.DB_NEW_VOTES);

        String json = TestUtil.json(topic);
        this.mockMvc.perform(post(URL_PREFIX + "/addBySessionId/" + SessionConstants.DB_ID)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isCreated());
    }
}
