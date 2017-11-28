package com.ftn.ZgradeProjekat.data;

import com.ftn.ZgradeProjekat.domain.Address;
import com.ftn.ZgradeProjekat.domain.Building;
import com.ftn.ZgradeProjekat.domain.User;
import com.ftn.ZgradeProjekat.repository.BuildingRepository;
import com.ftn.ZgradeProjekat.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by djuro on 11/27/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
public class UserRepositoryUnitTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() {
        entityManager.persist(new User("username","password"));
    }

    @Test
    public void testFindByUsername() {
        User user = this.userRepository.findByUsername("username");

        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
    }
}
