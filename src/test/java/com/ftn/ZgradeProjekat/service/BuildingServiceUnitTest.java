package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.Address;
import com.ftn.ZgradeProjekat.domain.Building;
import com.ftn.ZgradeProjekat.domain.DTO.BuildingDTO;
import com.ftn.ZgradeProjekat.repository.BuildingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * Created by djuro on 12/7/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuildingServiceUnitTest
{
    @Autowired
    BuildingService buildingService;

    @MockBean
    BuildingRepository buildingRepository;

    @Before
    public void setup() {
        given(
                buildingRepository.getOne(1L)
        ).willReturn(
                new Building(1L,new Address(1L,"Novi Sad","30","Bulevar Oslobodjenja","21000","Serbia"),
                        new Date(2017,8,8),null,null,
                        null,null,null,null)
        );

        given(
                buildingRepository.findById(1L)
        ).willReturn(
                new Building(1L,new Address(1L,"Novi Sad","30","Bulevar Oslobodjenja","21000","Serbia"),
                        new Date(2017,8,8),null,null,
                        null,null,null,null)
        );


        given(
                buildingRepository.save(new Building(new BuildingDTO(null, new Date(2017,8,8),
                        new Address(null,"Novi Sad","30","Bulevar Oslobodjenja","21000","Serbia"),
                        null,null,null,null)))
        ).willReturn(
                new Building(1L,new Address(1L,"Novi Sad","30","Bulevar Oslobodjenja","21000","Serbia"),
                        new Date(2017,8,8),null,null,
                        null,null,null,null)
        );
    }

    @Test
    public void testGetById() {
        BuildingDTO result = buildingService.getById(1L);
        assertEquals(new Date(2017,8,8),result.getDateOfConstruction());
        assertEquals((Long)1L,result.getId());
        assertEquals(new Address(1L,"Novi Sad","30","Bulevar Oslobodjenja","21000","Serbia"),result.getAdress());
    }

}
