package com.tracebucket.tron.event;

import com.tracebucket.tron.cqrs.support.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.Reactor;
import reactor.event.Event;

import java.util.Map;

/**
 * @author ffl
 * @since 16-09-2014
 * @version 0.1
 */

@Component
public class EventHandlerHelper {
    private static Logger log = LoggerFactory.getLogger(EventHandlerHelper.class);

    @Autowired
    private Reactor eventBus;

    public DomainEvent result(String event, Object o, Command command){
        DomainEvent domainEvent = DomainEvent.wrap(o)
                .from(command);
        eventBus.notify(event, domainEvent);
        return domainEvent;
    }

    public DomainEvent notify(String name, Object o){
        DomainEvent domainEvent = DomainEvent.wrap(o);
        eventBus.notify(name, domainEvent);
        return domainEvent;
    }

    public void notify(Map<String, Event> events){
        for(String key: events.keySet()){
            eventBus.notify(key, events.get(key));
            log.info("Publishing event " + key + " " + events.get(key).toString());
        }
    }


}
