package com.ftn.ZgradeProjekat.data;

import com.ftn.ZgradeProjekat.domain.Authority;
import com.ftn.ZgradeProjekat.domain.Building;
import com.ftn.ZgradeProjekat.domain.User;
import com.ftn.ZgradeProjekat.domain.UserAuthority;
import com.ftn.ZgradeProjekat.repository.AuthorityRepository;
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
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by djuro on 11/28/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
public class AuthorityRepositoryUnitTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    AuthorityRepository authorityRepository;

    @Before
    public void setUp() {
        Authority authority = new Authority();
        authority.setName("GUEST");
        entityManager.persist(authority);
    }

    @Test
    public void testFindByName() {
        Authority authority = this.authorityRepository.findByName("GUEST");

        assertEquals("GUEST", authority.getName());
        assertEquals(new HashSet<UserAuthority>(), authority.getUserAuthorities());
    }
}
