package com.tracebucket.tron.test.assembler.assembler.resource;

import com.tracebucket.tron.assembler.ResourceAssembler;
import com.tracebucket.tron.test.assembler.sample.BusinessLine;
import com.tracebucket.tron.test.assembler.sample.BusinessLineResource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sadath on 06-Apr-15.
 */
@Component
public class BusinessLineResourceAssembler extends ResourceAssembler<BusinessLineResource, BusinessLine> {

    @Override
    public BusinessLineResource toResource(BusinessLine entity, Class<BusinessLineResource> resourceClass) {
        BusinessLineResource businessLineResource = null;
        try {
            businessLineResource = resourceClass.newInstance();
            if (entity != null) {
                businessLineResource = new BusinessLineResource();
                businessLineResource.setUid(entity.getEntityId().getId());
                businessLineResource.setName(entity.getName());
                businessLineResource.setDescription(entity.getDescription());
            }
        } catch (InstantiationException ie) {

        } catch (IllegalAccessException iae) {

        }
        return businessLineResource;
    }

    @Override
    public Set<BusinessLineResource> toResources(Collection<BusinessLine> entities, Class<BusinessLineResource> resourceClass) {
        Set<BusinessLineResource> businessLines = new HashSet<BusinessLineResource>();
        if(entities != null) {
            Iterator<BusinessLine> iterator = entities.iterator();
            if(iterator.hasNext()) {
                BusinessLine businessLine = iterator.next();
                businessLines.add(toResource(businessLine, BusinessLineResource.class));
            }
        }
        return businessLines;
    }
}