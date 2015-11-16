package com.tracebucket.tron.ddd.jpa;

import com.tracebucket.tron.ddd.domain.BaseAggregateRoot;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.io.Serializable;
import java.util.List;

/**
 * @author ffl
 * @since 28-01-2015
 * @version 0.1
 */

@NoRepositoryBean
public class BaseAggregateRepositoryImpl<T extends BaseAggregateRoot, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseAggregateRepository<T, ID> {

    private EntityManager entityManager;

    private JpaEntityInformation<T, ?> entityInformation;

    public BaseAggregateRepositoryImpl(final JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    public BaseAggregateRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void delete(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        T entity = findOne(id);
        if(entity != null) {
            entity.setPassive(true);
            save(entity);
        }
    }

    @Override
    @Transactional
    public void delete(T entity) {
        Assert.notNull(entity, "The entity must not be null!");
        entity = entityManager.contains(entity) ? entity : entityManager.merge(entity);
        if(entity != null) {
            entity.setPassive(true);
            save(entity);
        }
    }

    @Override
    public T findOne(ID id) {
        T aggregate = entityManager.find(entityInformation.getJavaType(), id, LockModeType.OPTIMISTIC);
        if (aggregate != null && aggregate.isPassive())
            return null;
        return aggregate;
    }

    @Override
    public List<T> findAll() {
        return super.findAll();
    }

    @Transactional
    @Override
    public <S extends T> S save(S aggregate) {
        if (entityManager.contains(aggregate)){
            //locking Aggregate Root logically protects whole aggregate
            entityManager.lock(aggregate, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        }
        else{
            entityManager.persist(aggregate);
        }
        return aggregate;
    }

    /**
     * Delete Based On Tenant Id
     * @param id  primary key
     * @param tenantId  tenantId
     * @return void
     */
    @Override
    public void delete(ID id, String tenantId) {
        List<T> result = entityManager.createQuery("Select a from " + entityInformation.getEntityName() + " a where a.owner.organizationUID = '" + tenantId +"' and a.aggregateId.aggregateId = '"+ id + "'")
                .getResultList();
        if(result != null && result.size() == 1) {
            T entity = result.get(0);
            if(entity != null) {
                entity.setPassive(true);
                save(entity);
            }
        }
    }

    /**
     * Find Based On Tenant Id
     * @param id  primary key
     * @param tenantId  tenantId
     * @return found entity instance
     */
    @Override
    public T findOne(ID id, String tenantId) {
        List<T> result = entityManager.createQuery("Select a from " + entityInformation.getEntityName() + " a where a.owner.organizationUID = '" + tenantId +"' and a.aggregateId.aggregateId = '"+ id + "' and a.passive = false")
                .getResultList();
        if(result != null && result.size() == 1) {
            return result.get(0);
        }
        return null;
    }

    /**
     * Find All Based On Tenant Id
     * @param tenantId  tenantId
     * @return list of all found entity instances
     */
    @Override
    public List<T> findAll(String tenantId) {
        return entityManager.createQuery("Select a from " + entityInformation.getEntityName() + " a where a.owner.organizationUID = '" + tenantId + "' and a.passive = false")
                .getResultList();
    }
}