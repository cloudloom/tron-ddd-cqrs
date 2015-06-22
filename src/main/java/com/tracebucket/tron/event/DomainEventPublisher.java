package com.tracebucket.tron.event;

import com.tracebucket.tron.cqrs.support.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.bus.Event;
import reactor.bus.EventBus;

import java.util.Map;

/**
 * @author ffl
 * @since 16-09-2014
 * @version 0.1
 */

@Component
public class DomainEventPublisher {
    private static Logger log = LoggerFactory.getLogger(DomainEventPublisher.class);

    @Autowired
    private EventBus domainEventBus;

    public DomainEvent result(String event, Object o, Command command){
        DomainEvent domainEvent = DomainEvent.wrap(o)
                .from(command);
        domainEventBus.notify(event, domainEvent);
        return domainEvent;
    }

    public DomainEvent notify(String name, Object o){
        DomainEvent domainEvent = DomainEvent.wrap(o);
        domainEventBus.notify(name, domainEvent);
        return domainEvent;
    }

    public void notify(Map<String, Event> events){
        for(String key: events.keySet()){
            domainEventBus.notify(key, events.get(key));
            log.info("Publishing event " + key + " " + events.get(key).toString());
        }
    }


}
