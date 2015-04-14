package com.tracebucket.tron.service.impl;

import com.tracebucket.tron.ddd.annotation.DomainService;
import com.tracebucket.tron.ddd.annotation.PersistChanges;
import com.tracebucket.tron.ddd.domain.AggregateId;
import com.tracebucket.tron.domain.Organization;
import com.tracebucket.tron.domain.OrganizationUnit;
import com.tracebucket.tron.repository.jpa.OrganizationRepository;
import com.tracebucket.tron.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @author sadath
 * @modified ffl
 * @since 22-Jan-15
 *
 * Domain service
 */
@DomainService
public class OrganizationServiceImpl implements OrganizationService {
    private static Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Organization findOne(AggregateId aggregateId) {
        organizationRepository.flush();
        return organizationRepository.findOne(aggregateId);
    }

    @Override
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @Override
    public boolean delete(AggregateId organizationAggregateId) {
        Organization organization = organizationRepository.findOne(organizationAggregateId);
        if(organization != null) {
            organizationRepository.delete(organization);
            return organizationRepository.findOne(organizationAggregateId) == null ? true : false;
        }
        return false;
    }

    @Override
    @PersistChanges(repository = "organizationRepository")
    public Organization addOrganizationUnit(OrganizationUnit organizationUnit, AggregateId organizationAggregateId) {
        Organization organization = organizationRepository.findOne(organizationAggregateId);
        if(organization != null) {
            organization.addOrganizationUnit(organizationUnit);
            return organization;
        }
        return null;
    }

    @Override
    @PersistChanges(repository = "organizationRepository")
    public Organization addOrganizationUnitBelow(OrganizationUnit organizationUnit, OrganizationUnit parentOrganizationUnit, AggregateId organizationAggregateId) {
        Organization organization = organizationRepository.findOne(organizationAggregateId);
        if(organization != null) {
            organization.addOrganizationUnitBelow(organizationUnit, parentOrganizationUnit);
            return  organization;
        }
        return null;
    }

    @Override
    public Set<OrganizationUnit> getOrganizationUnits(AggregateId organizationAggregateId) {
        Organization organization = organizationRepository.findOne(organizationAggregateId);
        if(organization != null) {
            return organization.getOrganizationUnits();
        }
        return null;
    }
}
