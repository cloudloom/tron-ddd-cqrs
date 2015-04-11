package com.tracebucket.tron.builder;

import com.tracebucket.tron.domain.Organization;

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
        return new Organization(name, code, description, website, image);
    }

}