package com.ftn.ZgradeProjekat.data;

import com.ftn.ZgradeProjekat.domain.Address;
import com.ftn.ZgradeProjekat.domain.Building;
import com.ftn.ZgradeProjekat.repository.BuildingRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by djuro on 11/27/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class BuildingRepositoryUnitTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    BuildingRepository buildingRepository;

    @Before
    public void setUp() {
        //entityManager.persist(new Building(1L,null,new Date(2017,8,8),null,null,null,null,null));
        //entityManager.persist(new Building(2L,null,new Date(2017,8,7),null,null,null,null,null));
        //entityManager.persist(new Building(3L,null,new Date(2017,8,6),null,null,null,null,null));
        this.buildingRepository.save(new Building(1L,new Address(1L,"Novi Sad","30","Bulevar Oslobodjenja","21000","Serbia"),new Date(2017,8,8),null,null,null,null,null,null));
    }

    @Test
    public void testfindById() {
        Building building = this.buildingRepository.findById(1L);

        assertEquals((Long)1L, building.getId());
        assertEquals(new Date(2017,8,8), building.getDateOfConstruction());
        assertEquals("Novi Sad", building.getAddress().getCity());
        assertEquals("30", building.getAddress().getNumber());
    }

    @After
    public void tearDown()
    {
        this.buildingRepository.delete(1L);
    }

}
