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
        return resource;
    }

    public Set<T> toResources(Collection<E> entities, Class<T> resourceClass){
        Set<T> resources = new HashSet<T>();
        Stream<E> stream = entities.stream();
        stream.forEach(t -> resources.add(toResource(t, resourceClass)));
        return resources;
    }
}
