package com.ftn.ZgradeProjekat.web.controller;

import com.ftn.ZgradeProjekat.TestUtil;
import com.ftn.ZgradeProjekat.constants.SessionConstants;
import com.ftn.ZgradeProjekat.constants.SurveyConstants;
import com.ftn.ZgradeProjekat.constants.TopicConstants;
import com.ftn.ZgradeProjekat.domain.Answer;
import com.ftn.ZgradeProjekat.domain.DTO.LoginRequestDTO;
import com.ftn.ZgradeProjekat.domain.DTO.LoginResponseDTO;
import com.ftn.ZgradeProjekat.domain.Question;
import com.ftn.ZgradeProjekat.domain.Survey;
import com.ftn.ZgradeProjekat.domain.Topic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by EmulatE on 11-Dec-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations="classpath:test.properties")
public class SurveyControllerTest {
    private static final String URL_PREFIX = "/survey";

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
    public void addAnswer() throws Exception {
        Answer answer = new Answer();
        answer.setId(SurveyConstants.new_id);
        answer.setChoiced(SurveyConstants.new_choiced);
        answer.setRate(SurveyConstants.new_rate);
        answer.setText(SurveyConstants.new_text);



        String json = TestUtil.json(answer);
        this.mockMvc.perform(post(URL_PREFIX + "/addAnswer")
                .contentType(contentType)
                .header("X-Auth-Token", loginToken)
                .content(json))
                .andExpect(status().isCreated());

    }

    @Test
    public void testGetSurveys() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getAllSurveys"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(SurveyConstants.DB_COUNT_SURVEYS)));
    }


    @Test
    @Transactional
    @Rollback(true)
    public void addSurvey() throws Exception {
        Survey s = new Survey();
        s.setId(1L);
        s.setDescription("a");
        s.setBuildingId(-11L);
        s.setDateOfSurvey("1");
        s.setQuestions(new HashSet<>());

        String json = TestUtil.json(s);
        this.mockMvc.perform(post(URL_PREFIX + "/addSurvey")
                .contentType(contentType)
                .header("X-Auth-Token", loginToken)
                .content(json))
                .andExpect(status().isCreated());

    }


    @Test
    @Transactional
    @Rollback(true)
    public void addQuestion() throws Exception {
        Question q = new Question();
        q.setId(1L);
        q.setChoices("a");
        q.setSecondType(false);
        q.setThirdType(true);
        q.setAnswers(new HashSet<>());

        String json = TestUtil.json(q);
        this.mockMvc.perform(post(URL_PREFIX + "/addQuestion")
                .contentType(contentType)
                .header("X-Auth-Token", loginToken)
                .content(json))
                .andExpect(status().isCreated());

    }


    @Test
    public void testGetAnswers() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getAllAnswers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetQuestions() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getAllQuestions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)));
    }



}
