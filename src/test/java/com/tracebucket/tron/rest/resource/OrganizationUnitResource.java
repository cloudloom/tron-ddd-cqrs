package com.tracebucket.tron.rest.resource;

import com.tracebucket.tron.dictionary.OrganizationFunction;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sadath on 31-Mar-15.
 */
public class OrganizationUnitResource extends BaseResource {
    private String name;
    private String description;
    private OrganizationResource organization;
    private OrganizationUnitResource parent;
    private Set<OrganizationFunction> organizationFunctions = new HashSet<OrganizationFunction>(0);
    private Set<DepartmentResource> departments = new HashSet<DepartmentResource>(0);
    private Set<BusinessLineResource> businessLines = new HashSet<BusinessLineResource>(0);
    private Set<OrganizationUnitResource> children = new HashSet<OrganizationUnitResource>(0);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrganizationResource getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationResource organization) {
        this.organization = organization;
    }

    public OrganizationUnitResource getParent() {
        return parent;
    }

    public void setParent(OrganizationUnitResource parent) {
        this.parent = parent;
    }

    public Set<OrganizationFunction> getOrganizationFunctions() {
        return organizationFunctions;
    }

    public void setOrganizationFunctions(Set<OrganizationFunction> organizationFunctions) {
        this.organizationFunctions = organizationFunctions;
    }

    public Set<DepartmentResource> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentResource> departments) {
        this.departments = departments;
    }

    public Set<BusinessLineResource> getBusinessLines() {
        return businessLines;
    }

    public void setBusinessLines(Set<BusinessLineResource> businessLines) {
        this.businessLines = businessLines;
    }

    public Set<OrganizationUnitResource> getChildren() {
        return children;
    }

    public void setChildren(Set<OrganizationUnitResource> children) {
        this.children = children;
    }
}