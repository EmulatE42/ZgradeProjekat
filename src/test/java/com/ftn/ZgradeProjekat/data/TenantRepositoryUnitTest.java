package com.ftn.ZgradeProjekat.data;

import com.ftn.ZgradeProjekat.domain.Authority;
import com.ftn.ZgradeProjekat.domain.Tenant;
import com.ftn.ZgradeProjekat.domain.User;
import com.ftn.ZgradeProjekat.domain.UserAuthority;
import com.ftn.ZgradeProjekat.repository.AuthorityRepository;
import com.ftn.ZgradeProjekat.repository.TenantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TenantRepositoryUnitTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    TenantRepository tenantRepository;

    Integer id;

    @Before
    public void setUp() {
        Tenant tenant = new Tenant(new User("username","password"));
        tenant.setFirstname("Petar");
        tenant.setLastname("Petrovic");
        entityManager.persist(tenant);
        this.id = tenant.getId();
    }

    @Test
    public void testFindById() {
        Tenant tenant = this.tenantRepository.findById(this.id);

        assertEquals(this.id, tenant.getId());
        assertEquals("Petar" , tenant.getFirstname());
        assertEquals("Petrovic", tenant.getLastname());
        assertEquals("username", tenant.getUsername());
        assertEquals("password", tenant.getPassword());
    }

}
