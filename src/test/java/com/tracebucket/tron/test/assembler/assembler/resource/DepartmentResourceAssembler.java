package com.tracebucket.tron.test.assembler.assembler.resource;

import com.tracebucket.tron.assembler.ResourceAssembler;
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
public class DepartmentResourceAssembler extends ResourceAssembler<DepartmentResource, Department> {

    @Override
    public DepartmentResource toResource(Department entity, Class<DepartmentResource> resourceClass) {
        DepartmentResource departmentResource = null;
        try {
            departmentResource = resourceClass.newInstance();
            if (entity != null) {
                departmentResource = new DepartmentResource();
                departmentResource.setUid(entity.getEntityId().getId());
                departmentResource.setName(entity.getName());
                departmentResource.setDescription(entity.getDescription());
            }
        } catch (InstantiationException ie) {

        } catch (IllegalAccessException iae) {

        }
        return departmentResource;
    }

    @Override
    public Set<DepartmentResource> toResources(Collection<Department> entities, Class<DepartmentResource> resourceClass) {
        Set<DepartmentResource> departmentResources = new HashSet<DepartmentResource>();
        if(entities != null) {
            Iterator<Department> iterator = entities.iterator();
            if(iterator.hasNext()) {
                Department department = iterator.next();
                departmentResources.add(toResource(department, DepartmentResource.class));
            }
        }
        return departmentResources;
    }

}