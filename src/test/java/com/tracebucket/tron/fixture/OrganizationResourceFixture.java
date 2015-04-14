package com.tracebucket.tron.fixture;

import com.tracebucket.tron.builder.OrganizationResourceBuilder;
import com.tracebucket.tron.rest.resource.OrganizationResource;

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