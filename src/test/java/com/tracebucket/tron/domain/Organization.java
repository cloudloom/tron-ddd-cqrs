package com.tracebucket.tron.domain;
import com.tracebucket.tron.ddd.annotation.DomainMethod;
import com.tracebucket.tron.ddd.domain.BaseAggregateRoot;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by ffl on 11-04-2014.
 */
@Entity
@Table(name = "ORGANIZATION")
public class Organization extends BaseAggregateRoot {

    @Column(name = "CODE", nullable = false)
    @Basic(fetch = FetchType.EAGER)
    private String code;

    @Column(name = "NAME", nullable = false, unique = true)
    @Basic(fetch = FetchType.EAGER)
    private String name;

    @Column(name = "DESCRIPTION")
    @Basic(fetch = FetchType.EAGER)
    private String description;

    @Column(name = "WEBSITE", unique = true)
    @Basic(fetch = FetchType.EAGER)
    private String website;

    @Column(name = "IMAGE")
    @Basic(fetch = FetchType.EAGER)
    protected String image;

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrganizationUnit> organizationUnits = new HashSet<OrganizationUnit>(0);

    public Organization() {
    }

    public Organization(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public Organization(String name, String code, String description, String website, String image) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.website = website;
        this.image = image;
    }

    @DomainMethod(event = "OrganizationUnitAdded")
    public void addOrganizationUnit(OrganizationUnit organizationUnit){
        if(organizationUnit != null) {
            organizationUnit.setOrganization(this);
            this.organizationUnits.add(organizationUnit);
        }
    }

    @DomainMethod(event = "OrganizationUnitAddedBelow")
    public void addOrganizationUnitBelow(OrganizationUnit organizationUnit, OrganizationUnit parentOrganizationUnit){
        Optional<OrganizationUnit> parentOrganizationUnitFetched = organizationUnits.parallelStream()
                .filter(t -> t.getEntityId() == parentOrganizationUnit.getEntityId())
                .findFirst();

        if(parentOrganizationUnitFetched.isPresent()) {
            parentOrganizationUnitFetched.get().addChild(organizationUnit);
        }
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getWebsite() {
        return website;
    }

    public String getImage() {
        return image;
    }

    public Set<OrganizationUnit> getOrganizationUnits(){
        return this.organizationUnits;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setOrganizationUnits(Set<OrganizationUnit> organizationUnits) {
        this.organizationUnits = organizationUnits;
    }
}
