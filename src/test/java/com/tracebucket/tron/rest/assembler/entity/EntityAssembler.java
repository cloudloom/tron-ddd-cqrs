package com.tracebucket.tron.rest.assembler.entity;

import com.tracebucket.tron.rest.resource.BaseResource;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * User: ffl
 * Date: 8/4/14
 * Time: 3:17 PM
 */
public abstract class EntityAssembler<E, R extends BaseResource> {
    @Autowired
    private Mapper mapper;

    public EntityAssembler(){

    }

    public E toEntity(R resource, Class<E> entityClass){
        E entity = mapper.map(resource, entityClass);
        return entity;
    }

    public Set<E> toEntities(Collection<R> resources, Class<E> entityClass){
        Set<E> entities = new HashSet<E>(0);
        for(R resource : resources){
            E entity = mapper.map(resource, entityClass);
            entities.add(entity);
        }
        return entities;
    }
}
