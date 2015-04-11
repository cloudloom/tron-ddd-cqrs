package com.tracebucket.tron.rest.assembler.resource;

import com.tracebucket.tron.domain.BusinessLine;
import com.tracebucket.tron.rest.resource.BusinessLineResource;
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
    public BusinessLineResource toResource(BusinessLine entity) {
        BusinessLineResource businessLineResource = null;
        if(entity != null) {
            businessLineResource = new BusinessLineResource();
            businessLineResource.setUid(entity.getEntityId().getId());
            businessLineResource.setName(entity.getName());
            businessLineResource.setDescription(entity.getDescription());
        }
        return businessLineResource;
    }

    @Override
    public Set<BusinessLineResource> toResources(Collection<BusinessLine> entities) {
        Set<BusinessLineResource> businessLines = new HashSet<BusinessLineResource>();
        if(entities != null) {
            Iterator<BusinessLine> iterator = entities.iterator();
            if(iterator.hasNext()) {
                BusinessLine businessLine = iterator.next();
                businessLines.add(toResource(businessLine));
            }
        }
        return businessLines;
    }
}