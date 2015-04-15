package com.tracebucket.tron.assembler;

import com.tracebucket.tron.ddd.domain.AggregateId;
import com.tracebucket.tron.ddd.domain.BaseAggregateRoot;
import com.tracebucket.tron.ddd.domain.BaseEntity;
import com.tracebucket.tron.ddd.domain.EntityId;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
        if(resource.getUid() != null) {
            if(entity instanceof BaseEntity) {
                ((BaseEntity)entity).setEntityId(new EntityId(resource.getUid()));
            } else if(entity instanceof BaseAggregateRoot) {
                ((BaseAggregateRoot)entity).setAggregateId(new AggregateId(resource.getUid()));
            }
        }
        return entity;
    }

    public Set<E> toEntities(Collection<R> resources, Class<E> entityClass){
        Set<E> entities = new HashSet<E>(0);
        for(R resource : resources){
            entities.add(toEntity(resource, entityClass));
        }
        return entities;
    }
}
