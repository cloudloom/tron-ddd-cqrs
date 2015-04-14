package com.tracebucket.tron.rest.assembler.resource;

import com.tracebucket.tron.domain.OrganizationUnit;
import com.tracebucket.tron.rest.resource.OrganizationUnitResource;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sadath on 31-Mar-15.
 */
@Component
public class OrganizationUnitResourceAssembler extends ResourceAssembler<OrganizationUnitResource, OrganizationUnit> {
    @Autowired
    private Mapper mapper;

    @Autowired
    private BusinessLineResourceAssembler businessLineResourceAssembler;

    @Autowired
    private DepartmentResourceAssembler departmentResourceAssembler;

    public OrganizationUnitResource toResource(OrganizationUnit entity) {
        OrganizationUnitResource organizationUnitResource = null;//new DozerBeanMapper().map(entity, OrganizationUnitResource.class);
       if(entity != null) {
           organizationUnitResource = new OrganizationUnitResource();
           organizationUnitResource.setUid(entity.getEntityId().getId());
           organizationUnitResource.setName(entity.getName());
           organizationUnitResource.setDescription(entity.getDescription());
           organizationUnitResource.setBusinessLines(businessLineResourceAssembler.toResources(entity.getBusinessLines()));
           organizationUnitResource.setDepartments(departmentResourceAssembler.toResources(entity.getDepartments()));
           organizationUnitResource.setChildren(toResources(entity.getChildren()));
           organizationUnitResource.setOrganizationFunctions(entity.getOrganizationFunctions());
           organizationUnitResource.setParent(toResource(entity.getParent()));
       }
        return organizationUnitResource;
    }

    @Override
    public Set<OrganizationUnitResource> toResources(Collection<OrganizationUnit> entities) {
        Set<OrganizationUnitResource> organizationUnits = new HashSet<OrganizationUnitResource>();
        if(entities != null && entities.size() > 0) {
            Iterator<OrganizationUnit> iterator = entities.iterator();
            if(iterator.hasNext()) {
                OrganizationUnit organizationUnit = iterator.next();
                organizationUnits.add(toResource(organizationUnit));
            }
        }
        return organizationUnits;
    }
}