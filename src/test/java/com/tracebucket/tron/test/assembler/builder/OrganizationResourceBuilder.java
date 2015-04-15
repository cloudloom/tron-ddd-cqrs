package com.tracebucket.tron.test.assembler.builder;

import com.tracebucket.tron.test.assembler.sample.OrganizationResource;

/**
 * Created by sadath on 31-Mar-15.
 */
public class OrganizationResourceBuilder {
    private OrganizationResourceBuilder(){ }

    public static OrganizationResourceBuilder anOrganizationResourceBuilder(){
        return new OrganizationResourceBuilder();
    }

    public OrganizationResource build(String name, String code, String description){
        return new OrganizationResource(name, code, description);
    }

    public OrganizationResource build(String name, String code, String description, String website, String image){
        return new OrganizationResource(name, code, description, website, image);
    }
}