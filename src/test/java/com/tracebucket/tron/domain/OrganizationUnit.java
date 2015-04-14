package com.tracebucket.tron.domain;

import com.tracebucket.tron.ddd.domain.BaseEntity;
import com.tracebucket.tron.dictionary.OrganizationFunction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ffl on 11-04-2014.
 */
@Entity
@Table(name = "ORGANIZATION_UNIT")
public class OrganizationUnit extends BaseEntity {

    @Column(name = "NAME", nullable = false)
    @Basic(fetch = FetchType.EAGER)
    private String name;

    @Column(name = "DESCRIPTION")
    @Basic(fetch = FetchType.EAGER)
    private String description;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="ORGANIZATION__ID")
    private Organization organization;

    @ManyToOne(fetch = FetchType.EAGER)
    private OrganizationUnit parent;

    @ElementCollection(targetClass=OrganizationFunction.class, fetch = FetchType.EAGER)
    @JoinTable(name = "ORGANIZATION_UNIT_FUNCTION", joinColumns = @JoinColumn(name = "ORGANIZATION_UNIT__ID"))
    @Enumerated(EnumType.STRING)
    @Column(name = "ORGANIZATION_FUNCTION", nullable = false, columnDefinition = "ENUM('SALES','PURCHASE') default 'SALES'")
    private Set<OrganizationFunction> organizationFunctions = new HashSet<OrganizationFunction>(0);

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="ORGANIZATION_UNIT__ID", referencedColumnName="ID")
    private Set<Department> departments = new HashSet<Department>(0);

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="ORGANIZATION_UNIT__ID", referencedColumnName="ID")
    private Set<BusinessLine> businessLines = new HashSet<BusinessLine>(0);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<OrganizationUnit> children = new HashSet<OrganizationUnit>(0);

    public OrganizationUnit() {
    }

    public OrganizationUnit parent(){
        return this.parent;
    }

    public Set<OrganizationUnit> children(){
        return this.children;
    }

    public Organization owner(){
        return this.organization;
    }

    public OrganizationUnit function(OrganizationFunction organizationFunction){
        this.organizationFunctions.add(organizationFunction);
        return this;
    }

    public Set<OrganizationFunction> functions(){
        return this.organizationFunctions;
    }

    public OrganizationUnit department(Department department){
        this.departments.add(department);
        return this;
    }

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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public OrganizationUnit getParent() {
        return parent;
    }

    public void setParent(OrganizationUnit parent) {
        this.parent = parent;
    }

    public Set<OrganizationFunction> getOrganizationFunctions() {
        return organizationFunctions;
    }

    public void setOrganizationFunctions(Set<OrganizationFunction> organizationFunctions) {
        this.organizationFunctions = organizationFunctions;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Set<BusinessLine> getBusinessLines() {
        return businessLines;
    }

    public void setBusinessLines(Set<BusinessLine> businessLines) {
        this.businessLines = businessLines;
    }

    public Set<OrganizationUnit> getChildren() {
        return children;
    }

    public void setChildren(Set<OrganizationUnit> children) {
        this.children = children;
        for(OrganizationUnit child : children){
            child.setParent(this);
        }
    }

    public void addChild(OrganizationUnit child){
        child.setParent(this);
        this.children.add(child);
    }
}
