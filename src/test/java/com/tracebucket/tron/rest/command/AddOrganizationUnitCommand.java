package com.tracebucket.tron.rest.command;

import com.tracebucket.tron.rest.resource.OrganizationUnitResource;

/**
 * Created by sadath on 31-Mar-15.
 */
public class AddOrganizationUnitCommand {
    private String organizationId;
    private OrganizationUnitResource organizationUnit;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public OrganizationUnitResource getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(OrganizationUnitResource organizationUnit) {
        this.organizationUnit = organizationUnit;
    }
}