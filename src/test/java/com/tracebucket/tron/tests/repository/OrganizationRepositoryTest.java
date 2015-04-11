package com.tracebucket.tron.tests.repository;

import com.tracebucket.tron.config.*;
import com.tracebucket.tron.domain.Organization;
import com.tracebucket.tron.fixture.OrganizationFixture;
import com.tracebucket.tron.fixture.OrganizationUnitFixture;
import com.tracebucket.tron.repository.jpa.OrganizationRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created by sadath on 13-Jan-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes  = {ApplicationTestConfig.class, InfrastructureTestConfig.class, JPATestConfig.class, ServiceTestConfig.class, WebTestConfig.class})
@Transactional
public class OrganizationRepositoryTest {
    @Autowired
    private OrganizationRepository organizationRepository;

    private Organization organization = null;

    @Before
    public void setUp() throws Exception{

    }

    private void createOrganization() throws Exception{
        organization = OrganizationFixture.standardOrganization();
        organization = organizationRepository.save(organization);
    }

    @Test
    @Rollback(value = false)
    public void testCreate() throws Exception{
        createOrganization();
        Assert.assertNotNull(organization.getAggregateId());
    }

    @Test
    @Rollback(value = false)
    public void testUpdate() throws Exception {
        createOrganization();
        organization.addOrganizationUnit(OrganizationUnitFixture.standardOrganizationUnit());
        organization = organizationRepository.save(organization);
        Assert.assertNotNull(organization);
        Assert.assertNotNull(organization.getAggregateId());
        Assert.assertEquals(1, organization.getOrganizationUnits().size());
    }

    @Test
    @Rollback(value = false)
    public void testFindById() throws Exception {
        createOrganization();
        organization = organizationRepository.findOne(organization.getAggregateId());
        Assert.assertNotNull(organization);
    }

    @After
    public void tearDown(){
        if(organization != null && organization.getAggregateId() != null) {
            organizationRepository.delete(organization.getAggregateId());
            organization = organizationRepository.findOne(organization.getAggregateId());
            Assert.assertNull(organization);
        }
    }
}