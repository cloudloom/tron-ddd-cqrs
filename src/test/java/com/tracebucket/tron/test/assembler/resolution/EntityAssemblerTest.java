package com.tracebucket.tron.test.assembler.resolution;

import com.tracebucket.tron.assembler.AssemblerResolver;
import com.tracebucket.tron.assembler.EntityAssembler;
import com.tracebucket.tron.test.assembler.config.AssemblerTestConfig;
import com.tracebucket.tron.test.assembler.fixture.OrganizationResourceFixture;
import com.tracebucket.tron.test.assembler.fixture.SaleChannelResourceFixture;
import com.tracebucket.tron.test.assembler.sample.Organization;
import com.tracebucket.tron.test.assembler.sample.OrganizationResource;
import com.tracebucket.tron.test.assembler.sample.SaleChannel;
import com.tracebucket.tron.test.assembler.sample.SaleChannelResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sadath on 14-Apr-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AssemblerTestConfig.class})
public class EntityAssemblerTest {

    @Autowired
    private AssemblerResolver assemblerResolver;

    @Test
    public void testInit(){
        Assert.assertNotNull(assemblerResolver);
    }

    @Test
    public void testOrganizationEntityAssembler() {
        EntityAssembler entityAssembler = assemblerResolver.resolveEntityAssembler(Organization.class, OrganizationResource.class);
        Assert.assertNotNull(entityAssembler);
        Organization organization = (Organization)entityAssembler.toEntity(OrganizationResourceFixture.standardOrganization(), Organization.class);
        Assert.assertNotNull(organization);
        Assert.assertNotNull(organization.getAggregateId());
    }

    @Test
    public void testEntityAssembler() {
        EntityAssembler entityAssembler = assemblerResolver.resolveEntityAssembler(SaleChannel.class, SaleChannelResource.class);
        Assert.assertNotNull(entityAssembler);
        SaleChannel saleChannel = (SaleChannel)entityAssembler.toEntity(SaleChannelResourceFixture.standardSaleChannel(), SaleChannel.class);
        Assert.assertNotNull(saleChannel);
        Assert.assertNotNull(saleChannel.getEntityId());
    }

}
