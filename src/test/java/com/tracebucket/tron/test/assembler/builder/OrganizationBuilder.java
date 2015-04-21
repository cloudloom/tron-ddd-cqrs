package com.tracebucket.tron.test.assembler.builder;

import com.tracebucket.tron.ddd.domain.AggregateId;
import com.tracebucket.tron.test.assembler.sample.Organization;

import java.util.UUID;

/**
 * Created by sadath on 25-Nov-14.
 */
public class OrganizationBuilder {

    private OrganizationBuilder(){ }

    public static OrganizationBuilder anOrganizationBuilder(){
        return new OrganizationBuilder();
    }

    public Organization build(String name, String code, String description){
        return new Organization(name, code, description);
    }

    public Organization build(String name, String code, String description, String website, String image){
       Organization organization = new Organization(name, code, description, website, image);
        organization.setAggregateId(new AggregateId(UUID.randomUUID().toString()));
        return organization;
    }

}