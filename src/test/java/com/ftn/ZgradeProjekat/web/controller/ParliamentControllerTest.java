package com.ftn.ZgradeProjekat.web.controller;

import com.ftn.ZgradeProjekat.TestUtil;
import com.ftn.ZgradeProjekat.ZgradeProjekatApplication;
import com.ftn.ZgradeProjekat.constants.ParliamentConstants;
import com.ftn.ZgradeProjekat.constants.SessionConstants;
import com.ftn.ZgradeProjekat.domain.DTO.LoginRequestDTO;
import com.ftn.ZgradeProjekat.domain.DTO.LoginResponseDTO;
import com.ftn.ZgradeProjekat.domain.Parliament;
import com.ftn.ZgradeProjekat.domain.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

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
public class ParliamentControllerTest {

    private static final String URL_PREFIX = "/parlament";

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
    public void initParliament() throws Exception {
        Parliament parliament = new Parliament();
        Session session = new Session();
        session.setId(SessionConstants.DB_ID);
        session.setDate(SessionConstants.DB_DATE);
        session.setRecord(SessionConstants.DB_RECORD);
        session.setTimetable(SessionConstants.DB_TIMETABLE);

        Set<Session> sessions = new HashSet<>();
        sessions.add(session);
        parliament.setSessions(sessions);
        parliament.setId(ParliamentConstants.DB_ID);


        String json = TestUtil.json(parliament);
        this.mockMvc.perform(post(URL_PREFIX + "/add")
                .header("X-Auth-Token", loginToken)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllParliaments() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getParlaments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(ParliamentConstants.DB_COUNT_PARLIAMENTS)));
    }

    @Test
    public void testGetParliament() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getById/-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(ParliamentConstants.DB_ID));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSaveParliament() throws Exception {
        Parliament parliament = new Parliament();
        parliament.setId(ParliamentConstants.DB_NEW_ID);
        Session session = new Session();
        session.setDate(SessionConstants.DB_NEW_DATE);
        session.setRecord(SessionConstants.DB_NEW_RECORD);
        session.setTimetable(SessionConstants.DB_NEW_TIMETABLE);

        Set<Session> sessions = new HashSet<>();
        sessions.add(session);
        parliament.setSessions(sessions);

        String json = TestUtil.json(parliament);
        this.mockMvc.perform(post(URL_PREFIX + "/add")
                .contentType(contentType)
                .content(json))
                .andExpect(status().isCreated());
    }


    @Test
    public void testGetParliamentSessions() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getSessions/" + ParliamentConstants.DB_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(SessionConstants.DB_COUNT_SESSIONS)))
                .andExpect(jsonPath("$.[*].id").value(
                        hasItem(SessionConstants.DB_ID.intValue())))
                .andExpect(jsonPath("$.[*].record").value(
                        hasItem(SessionConstants.DB_RECORD)))
                .andExpect(jsonPath("$.[*].timetable").value(
                        hasItem(SessionConstants.DB_TIMETABLE)));
    }
}
