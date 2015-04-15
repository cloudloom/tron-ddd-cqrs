package com.tracebucket.tron.test.assembler.assembler.resource;

import com.tracebucket.tron.assembler.ResourceAssembler;
import com.tracebucket.tron.test.assembler.sample.Organization;
import com.tracebucket.tron.test.assembler.sample.OrganizationResource;
import com.tracebucket.tron.test.assembler.sample.OrganizationUnitResource;
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
public class OrganizationResourceAssembler extends ResourceAssembler<OrganizationResource, Organization> {
    @Autowired
    private Mapper mapper;

    @Autowired
    private OrganizationUnitResourceAssembler organizationUnitResourceAssembler;


    @Override
    public OrganizationResource toResource(Organization entity, Class<OrganizationResource> resourceClass) {
        OrganizationResource organizationResource = null;
        try {
            organizationResource = resourceClass.newInstance();
            if (entity != null) {
                organizationResource = new OrganizationResource();
                organizationResource.setUid(entity.getAggregateId().getId());
                organizationResource.setName(entity.getName());
                organizationResource.setDescription(entity.getDescription());
                organizationResource.setCode(entity.getCode());
                organizationResource.setImage(entity.getImage());
                organizationResource.setWebsite(entity.getWebsite());
                organizationResource.setOrganizationUnits(organizationUnitResourceAssembler.toResources(entity.getOrganizationUnits(), OrganizationUnitResource.class));
            }
        } catch (IllegalAccessException iae) {

        } catch (InstantiationException ie) {

        }
        return organizationResource;
    }

    @Override
    public Set<OrganizationResource> toResources(Collection<Organization> entities, Class<OrganizationResource> resourceClass) {
        Set<OrganizationResource> organizations = new HashSet<OrganizationResource>();
        if(entities != null && entities.size() > 0) {
            Iterator<Organization> iterator = entities.iterator();
            if(iterator.hasNext()) {
                Organization organization = iterator.next();
                organizations.add(toResource(organization, OrganizationResource.class));
            }
        }
        return organizations;    }
}
