package com.ftn.ZgradeProjekat.web.controller;

import com.ftn.ZgradeProjekat.TestUtil;
import com.ftn.ZgradeProjekat.domain.Address;
import com.ftn.ZgradeProjekat.domain.DTO.LoginRequestDTO;
import com.ftn.ZgradeProjekat.domain.DTO.LoginResponseDTO;
import com.ftn.ZgradeProjekat.domain.DTO.RegisterUserDTO;
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

import static com.ftn.ZgradeProjekat.constants.BuildingConstants.NUMBER_OF_RESPONSIBLE_PERSONS;
import static com.ftn.ZgradeProjekat.constants.LocationConstants.LOCATION_ID;
import static com.ftn.ZgradeProjekat.constants.UserConstants.*;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

/**
 * Created by djuro on 12/9/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations="classpath:test.properties")
public class UserControllerTest
{
    private static final String URL_PREFIX = "/api";
    private static final String URL_PREFIX_USER = "/user";


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
    }

    @Test
    public void testLogin() throws Exception {
        LoginRequestDTO login =  new LoginRequestDTO("a", "a");
        String json = TestUtil.json(login);

        mockMvc.perform(post(URL_PREFIX + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testRegisterUser() throws Exception {
        String json = TestUtil.json(REGISTER_USER_DTO);

        this.mockMvc.perform(post(URL_PREFIX+ "/registerUser")
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(USERNAME))
                .andExpect(jsonPath("$.role").value(ROLE));
    }

    @Test
    public void testGetAllTenants() throws Exception {

        this.mockMvc.perform(get(URL_PREFIX_USER+ "/getAllTenants" )
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",  hasSize(NUMBER_OF_TENANTS)));
    }

    @Test
    public void testGetAllInstitution() throws Exception {

        this.mockMvc.perform(get(URL_PREFIX_USER+ "/getAllInstitution" )
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",  hasSize(NUMBER_OF_INSTITUTION)));
    }

    @Test
    public void testGetAllTenantsFromBuilding() throws Exception {

        this.mockMvc.perform(get(URL_PREFIX_USER+ "/getAllTenantsFromBuilding/"+"-1" )
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetApartmentsOfTenant() throws Exception {

        this.mockMvc.perform(get(URL_PREFIX_USER+ "/getApartmentsOfTenant/"+"-3" )
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllFirms() throws Exception {

        this.mockMvc.perform(get(URL_PREFIX_USER+ "/getAllFirms" )
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetParliamentsOfTenant() throws Exception {

        this.mockMvc.perform(get(URL_PREFIX_USER+ "/getParliamentsOfTenant/"+"-2" )
                .header("X-Auth-Token", loginToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
