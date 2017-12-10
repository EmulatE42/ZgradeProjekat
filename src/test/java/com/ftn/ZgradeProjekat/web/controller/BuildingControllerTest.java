package com.ftn.ZgradeProjekat.web.controller;


import com.ftn.ZgradeProjekat.TestUtil;
import com.ftn.ZgradeProjekat.domain.Address;
import com.ftn.ZgradeProjekat.domain.DTO.*;
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
import java.util.Date;

import static com.ftn.ZgradeProjekat.constants.BuildingConstants.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by djuro on 12/9/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations="classpath:test.properties")
public class BuildingControllerTest
{
    private static final String URL_PREFIX = "/building";

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
                new LoginRequestDTO("a", "a"), LoginResponseDTO.class);
        loginToken = responseEntity.getBody().getToken();
        System.out.println(loginToken);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddBuilding() throws Exception {
        BuildingDTO building = BUILDING_DTO;

        String json = TestUtil.json(building);
        this.mockMvc.perform(post(URL_PREFIX+ "/add")
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.address.country").value(COUNTRY))
                .andExpect(jsonPath("$.address.city").value(CITY))
                .andExpect(jsonPath("$.address.number").value(NUMBER))
                .andExpect(jsonPath("$.address.street").value(STREET));

    }

    @Test
    public void testGetById() throws Exception {
        this.mockMvc.perform(get(URL_PREFIX+ "/findByBuildingId/" + BUILDING_ID)
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adress.street").value(STREET_1))
                .andExpect(jsonPath("$.id").value(BUILDING_ID));
    }

    @Test
    public void testGetAllBuildings() throws Exception {

        this.mockMvc.perform(get(URL_PREFIX+ "/getAllBuildings")
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",  hasSize(NUMBER_OF_BUILDINGS)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(BUILDING_ID.intValue())));

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteBuilding() throws Exception {

        this.mockMvc.perform(delete(URL_PREFIX+ "/deleteBuilding/"+"-1")
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddLocation() throws Exception {

        String json = TestUtil.json(LOCATION_DTO);
        this.mockMvc.perform(post(URL_PREFIX+ "/addLocationToBuilding")
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value(LOCATION_TYPE ))
                .andExpect(jsonPath("$.floor").value(LOCATION_FLOOR));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddResponsiblePerson() throws Exception {

        String json = TestUtil.json(RESPONSIBLE_PERSON_DTO);
        this.mockMvc.perform(post(URL_PREFIX+ "/addResponsiblePerson/"+"-1")
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(DESCRIPTION));
    }

    @Test
    public void testGetAllResponsiblePersons() throws Exception {

        this.mockMvc.perform(get(URL_PREFIX+ "/getAllResponsiblePersons/"+"-1")
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",  hasSize(NUMBER_OF_RESPONSIBLE_PERSONS)));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteResponsiblePerson() throws Exception {

        this.mockMvc.perform(delete(URL_PREFIX+ "/deleteResponsiblePerson/"+"-1" +  "/" + "-1")
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSetBuildingManager() throws Exception {

        this.mockMvc.perform(put(URL_PREFIX+ "/setBuildingManager/"+"-1" +  "/" + "-2")
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllResponsiblePersonsByLocationId() throws Exception {

        this.mockMvc.perform(get(URL_PREFIX+ "/getAllResponsiblePersonsByLocationId/"+"-1")
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",  hasSize(NUMBER_OF_RESPONSIBLE_PERSONS_OF_LOCATION )));
    }


}
