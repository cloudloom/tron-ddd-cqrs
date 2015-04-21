package com.tracebucket.tron.test.assembler.resolution;

import com.tracebucket.tron.assembler.AssemblerResolver;
import com.tracebucket.tron.assembler.ResourceAssembler;
import com.tracebucket.tron.test.assembler.config.AssemblerTestConfig;
import com.tracebucket.tron.test.assembler.fixture.OrganizationFixture;
import com.tracebucket.tron.test.assembler.fixture.SaleChannelFixture;
import com.tracebucket.tron.test.assembler.sample.*;
import com.tracebucket.tron.test.assembler.sample.OrganizationResource;
import com.tracebucket.tron.test.assembler.sample.SaleChannelResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ffl on 14-04-2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AssemblerTestConfig.class})
public class ResourceAssemblerTest {

	@Autowired
	private AssemblerResolver assemblerResolver;

	@Test
	public void testInit(){
		Assert.assertNotNull(assemblerResolver);
	}

    @Test
    public void testOrganizationResourceAssembler() {
        ResourceAssembler resourceAssembler = assemblerResolver.resolveResourceAssembler(OrganizationResource.class, Organization.class);
        Assert.assertNotNull(resourceAssembler);
        OrganizationResource organizationResource = (OrganizationResource)resourceAssembler.toResource(OrganizationFixture.standardOrganization(), OrganizationResource.class);
        Assert.assertNotNull(organizationResource);
    }

    @Test
    public void testResourceAssembler() {
        ResourceAssembler resourceAssembler = assemblerResolver.resolveResourceAssembler(SaleChannelResource.class, SaleChannel.class);
        Assert.assertNotNull(resourceAssembler);
        SaleChannelResource saleChannelResource = (SaleChannelResource)resourceAssembler.toResource(SaleChannelFixture.standardSaleChannel(), SaleChannelResource.class);
        Assert.assertNotNull(saleChannelResource);
        Assert.assertNotNull(saleChannelResource.getUid());
    }
}
