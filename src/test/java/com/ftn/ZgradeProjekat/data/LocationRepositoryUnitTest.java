package com.ftn.ZgradeProjekat.data;

import com.ftn.ZgradeProjekat.domain.Apartment;
import com.ftn.ZgradeProjekat.domain.Authority;
import com.ftn.ZgradeProjekat.domain.Location;
import com.ftn.ZgradeProjekat.domain.UserAuthority;
import com.ftn.ZgradeProjekat.repository.BuildingRepository;
import com.ftn.ZgradeProjekat.repository.LocationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by djuro on 11/28/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
public class LocationRepositoryUnitTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    LocationRepository locationRepository;

    Long id;

    @Before
    public void setUp() {
        Location location = new Apartment();
        entityManager.persist(location);
        this.id=location.getId();
    }

    @Test
    public void testFindByName() {
        Location location = this.locationRepository.findById(this.id);

        assertEquals(this.id, location.getId());
        assertEquals(null, location.getBuilding());
    }
}

