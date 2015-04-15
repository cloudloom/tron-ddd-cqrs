package com.tracebucket.tron.test.assembler.assembler.entity;

import com.tracebucket.tron.assembler.EntityAssembler;
import com.tracebucket.tron.ddd.domain.EntityId;
import com.tracebucket.tron.test.assembler.sample.BusinessLine;
import com.tracebucket.tron.test.assembler.sample.Department;
import com.tracebucket.tron.test.assembler.sample.OrganizationUnit;
import com.tracebucket.tron.test.assembler.sample.OrganizationUnitResource;
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
public class OrganizationUnitEntityAssembler extends EntityAssembler<OrganizationUnit, OrganizationUnitResource> {
   @Autowired
    private Mapper mapper;

    @Autowired
    private BusinessLineEntityAssembler businessLineEntityAssembler;

    @Autowired
    private DepartmentEntityAssembler departmentEntityAssembler;

    public OrganizationUnit toEntity(OrganizationUnitResource resource, Class<OrganizationUnit> entityClass) {
        OrganizationUnit organizationUnit = null;//new DozerBeanMapper().map(resource, entityClass);
        if(resource != null) {
            organizationUnit = new OrganizationUnit();
            if (resource.getUid() != null) {
                organizationUnit.setEntityId(new EntityId(resource.getUid()));
            }
            organizationUnit.setName(resource.getName());
            organizationUnit.setDescription(resource.getDescription());
            organizationUnit.setOrganizationFunctions(resource.getOrganizationFunctions());
            organizationUnit.setBusinessLines(businessLineEntityAssembler.toEntities(resource.getBusinessLines(), BusinessLine.class));
            organizationUnit.setDepartments(departmentEntityAssembler.toEntities(resource.getDepartments(), Department.class));
            organizationUnit.setChildren(toEntities(resource.getChildren(), OrganizationUnit.class));
            organizationUnit.setParent(toEntity(resource.getParent(), OrganizationUnit.class));
        }
        return organizationUnit;
    }

    @Override
    public Set<OrganizationUnit> toEntities(Collection<OrganizationUnitResource> resources, Class<OrganizationUnit> entityClass) {
        Set<OrganizationUnit> organizationUnits = new HashSet<OrganizationUnit>();
        if(resources != null) {
            Iterator<OrganizationUnitResource> iterator = resources.iterator();
            if(iterator.hasNext()) {
                OrganizationUnitResource organizationUnitResource = iterator.next();
                organizationUnits.add(toEntity(organizationUnitResource, OrganizationUnit.class));
            }
        }
        return organizationUnits;
    }
}