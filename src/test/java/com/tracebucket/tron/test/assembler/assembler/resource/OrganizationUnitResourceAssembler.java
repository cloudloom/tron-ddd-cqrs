package com.tracebucket.tron.test.assembler.assembler.resource;

import com.tracebucket.tron.assembler.AssemblerResolver;
import com.tracebucket.tron.assembler.ResourceAssembler;
import com.tracebucket.tron.test.assembler.sample.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sadath on 31-Mar-15.
 */
//@Component
public class OrganizationUnitResourceAssembler extends ResourceAssembler<OrganizationUnitResource, OrganizationUnit> {

    @Autowired
    private AssemblerResolver assemblerResolver;

    @Override
    public OrganizationUnitResource toResource(OrganizationUnit entity, Class<OrganizationUnitResource> resourceClass) {
        OrganizationUnitResource organizationUnitResource = null;
        try {
            organizationUnitResource = resourceClass.newInstance();
            if (entity != null) {
                organizationUnitResource = new OrganizationUnitResource();
                organizationUnitResource.setUid(entity.getEntityId().getId());
                organizationUnitResource.setName(entity.getName());
                organizationUnitResource.setDescription(entity.getDescription());
                organizationUnitResource.setBusinessLines(assemblerResolver.resolveResourceAssembler(BusinessLineResource.class, BusinessLine.class)
                        .toResources(entity.getBusinessLines(), BusinessLineResource.class));
                organizationUnitResource.setDepartments(assemblerResolver.resolveResourceAssembler(DepartmentResource.class, Department.class)
                        .toResources(entity.getDepartments(), DepartmentResource.class));
                organizationUnitResource.setChildren(toResources(entity.getChildren(), OrganizationUnitResource.class));
                organizationUnitResource.setOrganizationFunctions(entity.getOrganizationFunctions());
                organizationUnitResource.setParent(toResource(entity.getParent(), OrganizationUnitResource.class));
            }
        } catch (InstantiationException ie) {

        } catch (IllegalAccessException iae) {

        }
        return organizationUnitResource;
    }

    @Override
    public Set<OrganizationUnitResource> toResources(Collection<OrganizationUnit> entities, Class<OrganizationUnitResource> resourceClass) {
        Set<OrganizationUnitResource> organizationUnits = new HashSet<OrganizationUnitResource>();
        if(entities != null && entities.size() > 0) {
            Iterator<OrganizationUnit> iterator = entities.iterator();
            if(iterator.hasNext()) {
                OrganizationUnit organizationUnit = iterator.next();
                organizationUnits.add(toResource(organizationUnit, OrganizationUnitResource.class));
            }
        }
        return organizationUnits;
    }
}