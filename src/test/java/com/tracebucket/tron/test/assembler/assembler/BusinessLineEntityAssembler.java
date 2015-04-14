package com.tracebucket.tron.test.assembler.assembler;

import com.tracebucket.tron.assembler.EntityAssembler;
import com.tracebucket.tron.ddd.domain.EntityId;
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
public class BusinessLineEntityAssembler extends EntityAssembler<BusinessLine, BusinessLineResource> {

    @Override
    public BusinessLine toEntity(BusinessLineResource resource, Class<BusinessLine> entityClass) {
        BusinessLine businessLine = null;
        if(resource != null) {
            businessLine = new BusinessLine();
            if (resource.getUid() != null) {
                businessLine.setEntityId(new EntityId(resource.getUid()));
            }
            businessLine.setName(resource.getName());
            businessLine.setDescription(resource.getDescription());
        }
        return businessLine;
    }

    @Override
    public Set<BusinessLine> toEntities(Collection<BusinessLineResource> resources, Class<BusinessLine> entityClass) {
        Set<BusinessLine> businessLines = new HashSet<BusinessLine>();
        if(resources != null) {
            Iterator<BusinessLineResource> iterator = resources.iterator();
            if(iterator.hasNext()) {
                BusinessLineResource businessLineResource = iterator.next();
                businessLines.add(toEntity(businessLineResource, BusinessLine.class));
            }
        }
        return businessLines;
    }
}
