package com.tracebucket.tron.ddd.jpa;

import com.tracebucket.tron.ddd.domain.BaseAggregateRoot;
import com.tracebucket.tron.ddd.domain.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author ssm
 * @since 28-01-2015
 * @version 0.1
 */

public class CustomRepositoryFactoryBean <R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

    private static final Logger log = LoggerFactory.getLogger(CustomRepositoryFactoryBean.class);

    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {

        return new CustomRepositoryFactory(entityManager);
    }

   private static class CustomRepositoryFactory<T extends BaseAggregateRoot, ID extends Serializable> extends JpaRepositoryFactory {

        private EntityManager entityManager;

        public CustomRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
        }

/*       @Override
       protected Object getTargetRepository(RepositoryMetadata metadata) {
            return new BaseJpaRepositoryImpl<T, ID>((Class<T>) metadata.getDomainType(), entityManager);
       }*/

       @Override
       protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
           JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());
           if(information.getDomainType().getGenericSuperclass().getTypeName().equals(BaseAggregateRoot.class.getTypeName())) {
               return new BaseAggregateRepositoryImpl(entityInformation, entityManager);
           } else if(information.getDomainType().getGenericSuperclass().getTypeName().equals(BaseEntity.class.getTypeName())) {
               return new SimpleJpaRepository(entityInformation, entityManager);
           }
           return new SimpleJpaRepository(entityInformation, entityManager);
       }

       protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseAggregateRepository.class;
        }
    }
}
