package com.tracebucket.tron.ddd.jpa;

import com.tracebucket.tron.ddd.domain.BaseEntity;
import com.tracebucket.tron.ddd.domain.EntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author ssm
 * @since 06-02-2015
 * @version 0.1
 */

@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {
    public T findByEntityId(EntityId entityId);
}