package com.tracebucket.tron.service;

import com.tracebucket.tron.ddd.domain.AggregateId;
import com.tracebucket.tron.domain.Organization;
import com.tracebucket.tron.domain.OrganizationUnit;

import java.util.List;
import java.util.Set;

/**
 * Created by sadath on 23-Jan-15.
 */
public interface OrganizationService {
    public Organization save(Organization organization);
    public Organization findOne(AggregateId aggregateId);
    public List<Organization> findAll();
    public boolean delete(AggregateId organizationAggregateId);
    public Organization addOrganizationUnit(OrganizationUnit organizationUnit, AggregateId organizationAggregateId);
    public Organization addOrganizationUnitBelow(OrganizationUnit organizationUnit, OrganizationUnit parentOrganizationUnit, AggregateId organizationAggregateId);
    public Set<OrganizationUnit> getOrganizationUnits(AggregateId organizationAggregateId);
}