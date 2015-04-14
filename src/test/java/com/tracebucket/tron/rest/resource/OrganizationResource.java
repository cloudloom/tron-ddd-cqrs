package com.tracebucket.tron.rest.resource;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sadath on 31-Mar-15.
 */
public class OrganizationResource extends BaseResource {
    private String code;
    private String name;
    private String description;
    private String website;
    protected String image;
    private Set<OrganizationUnitResource> organizationUnits = new HashSet<OrganizationUnitResource>(0);

    public OrganizationResource() {
    }

    public OrganizationResource(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public OrganizationResource(String name, String code, String description, String website, String image) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.website = website;
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<OrganizationUnitResource> getOrganizationUnits() {
        return organizationUnits;
    }

    public void setOrganizationUnits(Set<OrganizationUnitResource> organizationUnits) {
        this.organizationUnits = organizationUnits;
    }
}