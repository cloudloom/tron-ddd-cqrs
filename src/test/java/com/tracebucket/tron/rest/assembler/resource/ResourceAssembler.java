package com.tracebucket.tron.rest.assembler.resource;

import com.tracebucket.tron.rest.resource.BaseResource;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * User: ffl
 * Date: 2/4/14
 * Time: 6:22 PM
 */
public abstract class ResourceAssembler<T extends BaseResource, E> {
    @Autowired
    private Mapper mapper;


    public ResourceAssembler(){

    }

    public T toResource(E entity){
        Class<T> resourceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T resource = mapper.map(entity, resourceClass);
        return resource;
    }

    public Set<T> toResources(Collection<E> entities){
        Class<T> resourceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Set<T> resources = new HashSet<T>();
        for(E entity: entities){
            T resource = mapper.map(entity, resourceClass);
            resources.add(resource);
        }
        return resources;
    }
}
