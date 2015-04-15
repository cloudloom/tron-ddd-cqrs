package com.tracebucket.tron.test.assembler.fixture;

import com.tracebucket.tron.test.assembler.builder.OrganizationResourceBuilder;
import com.tracebucket.tron.test.assembler.sample.OrganizationResource;

import java.util.Date;
import java.util.UUID;

/**
 * Created by sadath on 31-Mar-15.
 */
public class OrganizationResourceFixture {
    public static OrganizationResource standardOrganization() {
        OrganizationResource organization = OrganizationResourceBuilder.anOrganizationResourceBuilder()
                .build("Organization " + new Date().getTime(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), "image");
        return organization;
    }
}