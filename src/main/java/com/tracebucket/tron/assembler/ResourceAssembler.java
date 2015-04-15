package com.tracebucket.tron.assembler;

import com.tracebucket.tron.ddd.domain.BaseAggregateRoot;
import com.tracebucket.tron.ddd.domain.BaseEntity;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * User: ffl
 * Date: 2/4/14
 * Time: 6:22 PM
 */
@Component("resourceAssembler")
public class ResourceAssembler<T extends BaseResource, E> {
    @Autowired
    private Mapper mapper;

    public ResourceAssembler(){

    }

    public T toResource(E entity, Class<T> resourceClass){
        T resource = mapper.map(entity, resourceClass);
        if(entity instanceof BaseEntity) {
            resource.setUid(((BaseEntity) entity).getEntityId().getId());
        } else if(entity instanceof BaseAggregateRoot) {
            resource.setUid(((BaseAggregateRoot) entity).getAggregateId().getId());
        }
        return resource;
    }

    public Set<T> toResources(Collection<E> entities, Class<T> resourceClass){
        Set<T> resources = new HashSet<T>();
        for(E entity: entities){
            resources.add(toResource(entity, resourceClass));
        }
        return resources;
    }
}
