package com.tracebucket.tron.tests.service;

import com.tracebucket.tron.config.*;
import com.tracebucket.tron.domain.Organization;
import com.tracebucket.tron.domain.OrganizationUnit;
import com.tracebucket.tron.fixture.OrganizationFixture;
import com.tracebucket.tron.fixture.OrganizationUnitFixture;
import com.tracebucket.tron.service.OrganizationService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sadath on 13-Jan-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes  = {ApplicationTestConfig.class, InfrastructureTestConfig.class, JPATestConfig.class, ServiceTestConfig.class, WebTestConfig.class})
public class OrganizationServiceTest {
    @Autowired
    private OrganizationService organizationService;

    private Organization organization = null;

    @Before
    public void setUp() throws Exception{

    }

    private void createOrganization() throws Exception{
        organization = OrganizationFixture.standardOrganization();
        organization = organizationService.save(organization);
    }

    @Test
    public void testCreate() throws Exception {
        createOrganization();
        Assert.assertNotNull(organization.getAggregateId());
    }

    @Test
    public void testAddOrganizationUnit() throws Exception {
        createOrganization();
        organization = organizationService.addOrganizationUnit(OrganizationUnitFixture.standardOrganizationUnit(), organization.getAggregateId());
        Assert.assertNotNull(organization);
        Assert.assertNotNull(organization.getOrganizationUnits());
        Assert.assertEquals(1, organization.getOrganizationUnits().size());
    }

    @Test
    public void testAddOrganizationUnitBelow() throws Exception {
        createOrganization();
        organization = organizationService.addOrganizationUnit(OrganizationUnitFixture.standardOrganizationUnit(), organization.getAggregateId());
        Assert.assertNotNull(organization);
        Assert.assertNotNull(organization.getOrganizationUnits());
        Assert.assertEquals(1, organization.getOrganizationUnits().size());
        OrganizationUnit childOrganizationUnit = OrganizationUnitFixture.standardOrganizationUnit();
        OrganizationUnit parentOrganizationUnit = null;
        for(OrganizationUnit organizationUnit : organization.getOrganizationUnits()) {
            parentOrganizationUnit = organizationUnit;
        }
        organization = organizationService.addOrganizationUnitBelow(childOrganizationUnit, parentOrganizationUnit, organization.getAggregateId());
        Assert.assertNotNull(organization);
        Assert.assertNotNull(organization.getOrganizationUnits());
        Assert.assertEquals(1, organization.getOrganizationUnits().size());
    }

    @Test
    public void testGetOrganizationUnits() throws Exception {
        createOrganization();
        organization = organizationService.addOrganizationUnit(OrganizationUnitFixture.standardOrganizationUnit(), organization.getAggregateId());
        Assert.assertNotNull(organization);
        Assert.assertNotNull(organization.getOrganizationUnits());
        Assert.assertEquals(1, organization.getOrganizationUnits().size());
    }

    @Test
    public void testFindById() throws Exception {
        createOrganization();
        organization = organizationService.findOne(organization.getAggregateId());
        Assert.assertNotNull(organization);
    }

    @After
    public void tearDown(){
        if(organization != null && organization.getAggregateId() != null) {
            organizationService.delete(organization.getAggregateId());
            organization = organizationService.findOne(organization.getAggregateId());
            Assert.assertNull(organization);
        }
    }
}