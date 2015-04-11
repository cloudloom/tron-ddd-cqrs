package com.tracebucket.tron.tests.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.tracebucket.tron.config.*;
import com.tracebucket.tron.fixture.OrganizationResourceFixture;
import com.tracebucket.tron.fixture.OrganizationUnitResourceFixture;
import com.tracebucket.tron.rest.command.AddOrganizationUnitCommand;
import com.tracebucket.tron.rest.resource.OrganizationResource;
import com.tracebucket.tron.service.OrganizationService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by sadath on 10-Feb-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes  = {ApplicationTestConfig.class, InfrastructureTestConfig.class, JPATestConfig.class, ServiceTestConfig.class, WebTestConfig.class})
public class OrganizationControllerTest {

    private static final Logger log = LoggerFactory.getLogger(OrganizationControllerTest.class);

    private static MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrganizationService organizationService;

    private OrganizationResource organization = null;

    @Before
    public void setUp(){
        mockMvc = webAppContextSetup(this.wac).build();
    }

    private void createOrganization() throws Exception{
        organization = OrganizationResourceFixture.standardOrganization();
        MvcResult mvcResult = null;
        log.info("Add Organization : "+ objectMapper.writeValueAsString(organization));
        mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/organization")
                        .content(objectMapper.writeValueAsString(organization))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        organization = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), OrganizationResource.class);
        Assert.assertNotNull(organization);
    }

    @Test
    public void testCreate() throws Exception {
        createOrganization();
        Assert.assertNotNull(organization.getUid());
    }

    @Test
    public void testAddOrganizationUnit() throws Exception{
        createOrganization();

        AddOrganizationUnitCommand addOrganizationUnitCommand = new AddOrganizationUnitCommand();
        addOrganizationUnitCommand.setOrganizationId(organization.getUid().toString());
        addOrganizationUnitCommand.setOrganizationUnit(OrganizationUnitResourceFixture.standardOrganizationUnitResource());

        MvcResult mvcResult = null;

        log.info("Add Organization Unit : "+ objectMapper.writeValueAsString(addOrganizationUnitCommand));
        mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/organization/organizationunit/")
                        .content(objectMapper.writeValueAsString(addOrganizationUnitCommand))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(request().asyncStarted())
                .andReturn();
        mvcResult.getAsyncResult();
        this.mockMvc.perform(asyncDispatch(mvcResult));

        if(mvcResult.getAsyncResult() instanceof OrganizationResource) {
            organization = (OrganizationResource) mvcResult.getAsyncResult();
        }
        Assert.assertNotNull(organization);
        Assert.assertNotNull(organization.getUid());
        Assert.assertNotNull(organization.getOrganizationUnits());
        Assert.assertEquals(1, organization.getOrganizationUnits().size());
    }

    @Test
    public void testFindOne() throws Exception {
        createOrganization();
        MvcResult mvcResult = null;
        log.info("Find Organization By UID : "+ organization.getUid());
        mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/organization/"+organization.getUid())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String uid = organization.getUid();
        organization = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), OrganizationResource.class);
        Assert.assertNotNull(organization);
        Assert.assertNotNull(organization.getUid());
        Assert.assertEquals(uid, organization.getUid());
    }

    @After
    public void tearDown() throws Exception{
        if(organization != null && organization.getUid() != null) {
            MvcResult mvcResult = null;
            log.info("Delete Organization By UID : "+ organization.getUid());
            mvcResult = mockMvc
                    .perform(MockMvcRequestBuilders.delete("/organization/"+organization.getUid())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            Boolean status = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Boolean.class);
            Assert.assertTrue(status);
        }
    }

}