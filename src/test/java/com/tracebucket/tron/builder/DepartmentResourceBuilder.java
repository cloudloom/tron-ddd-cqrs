package com.tracebucket.tron.builder;

import com.tracebucket.tron.rest.resource.DepartmentResource;

/**
 * Created by sadath on 31-Mar-15.
 */
public class DepartmentResourceBuilder {
    private String name;
    private String description;

    public DepartmentResourceBuilder(){ }

    public static DepartmentResourceBuilder aDepartmentResource(){
        return new DepartmentResourceBuilder();
    }

    public DepartmentResourceBuilder withName(String name){
        this.name = name;
        return this;
    }

    public DepartmentResourceBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public DepartmentResource build(){
        DepartmentResource department = new DepartmentResource();
        department.setName(name);
        department.setDescription(description);
        return department;
    }
}
