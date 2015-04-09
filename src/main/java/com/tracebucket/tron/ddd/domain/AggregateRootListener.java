package com.tracebucket.tron.ddd.domain;

import com.tracebucket.tron.ddd.support.EventRegistry;
import com.tracebucket.tron.event.EventHandlerHelper;
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
    private EventHandlerHelper eventHandlerHelper;

    @Autowired
    private EventRegistry eventRegistry;

    @PostPersist
    @PostUpdate
    public void publishEvents(BaseAggregateRoot aggregateRoot){

        Set<EventModel> eventModels = eventRegistry.events(aggregateRoot);
        eventModels.stream()
                .forEach(eventModel -> {
                    eventHandlerHelper.notify(eventModel.getEvent(), eventModel);
                    log.info("Publishing event " + eventModel.getEvent()+ " for " + eventModel.getAggregateRoot().getClass().getSimpleName() + " having instance " + eventModel.getAggregateRoot().instanceId());
                });
        eventRegistry.deleteInstanceEvents(aggregateRoot);

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
