package com.tracebucket.tron.test.assembler.assembler.entity;

import com.tracebucket.tron.assembler.EntityAssembler;
import com.tracebucket.tron.ddd.domain.EntityId;
import com.tracebucket.tron.test.assembler.sample.Department;
import com.tracebucket.tron.test.assembler.sample.DepartmentResource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sadath on 06-Apr-15.
 */
@Component
public class DepartmentEntityAssembler extends EntityAssembler<Department, DepartmentResource> {

    @Override
    public Department toEntity(DepartmentResource resource, Class<Department> entityClass) {
        Department department = null;
        if(resource != null) {
            department = new Department();
            if (resource.getUid() != null) {
                department.setEntityId(new EntityId(resource.getUid()));
            }
            department.setName(resource.getName());
            department.setDescription(resource.getDescription());
        }
        return department;
    }

    @Override
    public Set<Department> toEntities(Collection<DepartmentResource> resources, Class<Department> entityClass) {
        Set<Department> departments = new HashSet<Department>();
        if(resources != null) {
            Iterator<DepartmentResource> iterator = resources.iterator();
            if(iterator.hasNext()) {
                DepartmentResource organizationUnitResource = iterator.next();
                departments.add(toEntity(organizationUnitResource, Department.class));
            }
        }
        return departments;
    }
}