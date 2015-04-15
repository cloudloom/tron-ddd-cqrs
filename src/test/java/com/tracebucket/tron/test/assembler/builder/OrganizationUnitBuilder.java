package com.tracebucket.tron.test.assembler.builder;

import com.tracebucket.tron.test.assembler.sample.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sadath on 25-Nov-14.
 */
public class OrganizationUnitBuilder {
    private String name;
    private String description;
    private Organization organization;
    private OrganizationUnit parent;
    private Set<OrganizationFunction> organizationFunctions = new HashSet<OrganizationFunction>(0);
    private Set<Department> departments = new HashSet<Department>(0);
    private Set<BusinessLine> businessLines = new HashSet<BusinessLine>(0);
    private Set<OrganizationUnit> children = new HashSet<OrganizationUnit>(0);

    private OrganizationUnitBuilder(){ }

    public static OrganizationUnitBuilder anOrganizationUnitBuilder(){
        return new OrganizationUnitBuilder();
    }

    public OrganizationUnitBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public OrganizationUnitBuilder withName(String name){
        this.name = name;
        return this;
    }

    public OrganizationUnitBuilder withOrganization(Organization organization){
        this.organization = organization;
        return this;
    }

    public OrganizationUnitBuilder withParent(OrganizationUnit parent){
        this.parent = parent;
        return this;
    }

    public OrganizationUnitBuilder withOrganizationFunctions(Set<OrganizationFunction> organizationFunctions){
        this.organizationFunctions = organizationFunctions;
        return this;
    }

    public OrganizationUnitBuilder withDepartments(Set<Department> departments){
        this.departments = departments;
        return this;
    }

    public OrganizationUnitBuilder withBusinessLines(Set<BusinessLine> businessLines){
        this.businessLines = businessLines;
        return this;
    }

    public OrganizationUnitBuilder withChildren(Set<OrganizationUnit> children){
        this.children = children;
        return this;
    }

    public OrganizationUnit build(){
        OrganizationUnit organizationUnit = new OrganizationUnit();
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
