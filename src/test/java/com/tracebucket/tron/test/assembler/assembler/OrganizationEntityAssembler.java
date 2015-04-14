package com.tracebucket.tron.test.assembler.assembler;

import com.tracebucket.tron.assembler.EntityAssembler;
import com.tracebucket.tron.ddd.domain.AggregateId;
import com.tracebucket.tron.test.assembler.sample.Organization;
import com.tracebucket.tron.test.assembler.sample.OrganizationResource;
import com.tracebucket.tron.test.assembler.sample.OrganizationUnit;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sadath on 06-Apr-15.
 */
@Component
public class OrganizationEntityAssembler extends EntityAssembler<Organization, OrganizationResource> {

    @Autowired
    private Mapper mapper;

    @Autowired
    private OrganizationUnitEntityAssembler organizationUnitEntityAssembler;

    public Organization toEntity(OrganizationResource resource, Class<Organization> entityClass) {
        Organization organization = null;//mapper.map(resource, entityClass);
        if(resource != null) {
            organization = new Organization();
            if (resource.getUid() != null) {
                organization.setAggregateId(new AggregateId(resource.getUid()));
            }
            organization.setName(resource.getName());
            organization.setCode(resource.getCode());
            organization.setDescription(resource.getDescription());
            organization.setImage(resource.getImage());
            organization.setWebsite(resource.getWebsite());
            organization.setOrganizationUnits(organizationUnitEntityAssembler.toEntities(resource.getOrganizationUnits(), OrganizationUnit.class));
        }
        return organization;
    }

    @Override
    public Set<Organization> toEntities(Collection<OrganizationResource> resources, Class<Organization> entityClass) {
        Set<Organization> organizations = new HashSet<Organization>();
        if(resources != null) {
            Iterator<OrganizationResource> iterator = resources.iterator();
            if(iterator.hasNext()) {
                OrganizationResource organizationResource = iterator.next();
                organizations.add(toEntity(organizationResource, Organization.class));
            }
        }
        return organizations;
    }
}
