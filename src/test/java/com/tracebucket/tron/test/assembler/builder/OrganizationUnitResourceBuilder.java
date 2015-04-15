package com.tracebucket.tron.test.assembler.builder;

import com.tracebucket.tron.test.assembler.sample.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sadath on 31-Mar-15.
 */
public class OrganizationUnitResourceBuilder {
    private String name;
    private String description;
    private OrganizationResource organization;
    private OrganizationUnitResource parent;
    private Set<OrganizationFunction> organizationFunctions = new HashSet<OrganizationFunction>(0);
    private Set<DepartmentResource> departments = new HashSet<DepartmentResource>(0);
    private Set<BusinessLineResource> businessLines = new HashSet<BusinessLineResource>(0);
    private Set<OrganizationUnitResource> children = new HashSet<OrganizationUnitResource>(0);

    private OrganizationUnitResourceBuilder(){ }

    public static OrganizationUnitResourceBuilder anOrganizationUnitResourceBuilder(){
        return new OrganizationUnitResourceBuilder();
    }

    public OrganizationUnitResourceBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public OrganizationUnitResourceBuilder withName(String name){
        this.name = name;
        return this;
    }

    public OrganizationUnitResourceBuilder withOrganization(OrganizationResource organization){
        this.organization = organization;
        return this;
    }

    public OrganizationUnitResourceBuilder withParent(OrganizationUnitResource parent){
        this.parent = parent;
        return this;
    }

    public OrganizationUnitResourceBuilder withOrganizationFunctions(Set<OrganizationFunction> organizationFunctions){
        this.organizationFunctions = organizationFunctions;
        return this;
    }

    public OrganizationUnitResourceBuilder withDepartments(Set<DepartmentResource> departments){
        this.departments = departments;
        return this;
    }

    public OrganizationUnitResourceBuilder withBusinessLines(Set<BusinessLineResource> businessLines){
        this.businessLines = businessLines;
        return this;
    }

    public OrganizationUnitResourceBuilder withChildren(Set<OrganizationUnitResource> children){
        this.children = children;
        return this;
    }

    public OrganizationUnitResource build(){
        OrganizationUnitResource organizationUnit = new OrganizationUnitResource();
        organizationUnit.setName(name);
        organizationUnit.setDescription(description);
        organizationUnit.setBusinessLines(businessLines);
        organizationUnit.setChildren(children);
        organizationUnit.setDepartments(departments);
        organizationUnit.setOrganizationFunctions(organizationFunctions);
        organizationUnit.setParent(parent);
        organizationUnit.setOrganization(organization);
        return organizationUnit;
    }
}