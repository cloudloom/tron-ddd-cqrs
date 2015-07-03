package com.tracebucket.tron.assembler;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * User: ffl
 * Date: 8/4/14
 * Time: 3:17 PM
 */
@Component("entityAssembler")
public class EntityAssembler<E, R extends BaseResource> {
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
        Stream<R> stream = resources.stream();
        stream.forEach(t -> entities.add(toEntity(t, entityClass)));
        return entities;
    }
}
