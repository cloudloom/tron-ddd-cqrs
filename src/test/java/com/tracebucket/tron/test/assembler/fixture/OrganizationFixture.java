package com.tracebucket.tron.test.assembler.fixture;

import com.tracebucket.tron.test.assembler.builder.OrganizationBuilder;
import com.tracebucket.tron.test.assembler.sample.Organization;

import java.util.Date;
import java.util.UUID;

/**
 * Created by sadath on 25-Nov-14.
 */
public class OrganizationFixture {
    public static Organization standardOrganization() {
        Organization organization = OrganizationBuilder.anOrganizationBuilder()
                .build("Organization " + new Date().getTime(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), "image");
        return organization;
    }
}
