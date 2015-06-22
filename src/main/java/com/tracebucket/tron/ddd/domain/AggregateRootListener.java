package com.tracebucket.tron.ddd.domain;

import com.tracebucket.tron.ddd.support.DomainEventRegistry;
import com.tracebucket.tron.event.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import java.util.Set;

/**
 * @author ffl
 * @since 13-01-2015
 * @version 0.1
 */

public class AggregateRootListener extends AuditingEntityListener {
    private static Logger log = LoggerFactory.getLogger(AggregateRootListener.class);

    @Autowired
    private AutowireCapableBeanFactory spring;

    @Autowired
    private DomainEventPublisher domainEventPublisher;

    @Autowired
    private DomainEventRegistry domainEventRegistry;

    @PostPersist
    @PostUpdate
    public void publishEvents(BaseAggregateRoot aggregateRoot){

        Set<EventModel> eventModels = domainEventRegistry.events(aggregateRoot);
        eventModels.stream()
                .forEach(eventModel -> {
                    domainEventPublisher.notify(eventModel.getEvent(), eventModel);
                    log.info("Publishing event " + eventModel.getEvent()+ " for " + eventModel.getAggregateRoot().getClass().getSimpleName() + " having instance " + eventModel.getAggregateRoot().instanceId());
                });
        domainEventRegistry.deleteInstanceEvents(aggregateRoot);

    }

   /* @PostPersist
    @PostUpdate
    public void publishEvents(BaseAggregateRoot aggregateRoot){
        log.info(aggregateRoot.getEvents().toString());
        aggregateRoot.getEvents().stream()
                .forEach(event -> {
                    eventHandlerHelper.notify(event, aggregateRoot);
                    log.info("Publishing " + event + " " + aggregateRoot.toString());
                });
    }*/

    @PostLoad
    public void wire(BaseAggregateRoot aggregateRoot){
        spring.autowireBean(aggregateRoot);
    }

    @PrePersist
    public void init(BaseAggregateRoot aggregateRoot){
        /*if(aggregateRoot.getAggregateId() == null){
            aggregateRoot.aggregateId = AggregateId.generate();

        }*/
    }


}
