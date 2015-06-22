package com.tracebucket.tron.ddd.support;


import com.tracebucket.tron.ddd.domain.BaseAggregateRoot;
import com.tracebucket.tron.ddd.domain.EventModel;

import java.util.Set;

/**
 * @author ffl
 * @since 30-01-2015
 * @version 0.1
 */

public interface DomainEventRegistry {
    public void addEvent(BaseAggregateRoot aggregateRoot, String event);
    public Set<EventModel> events(BaseAggregateRoot aggregateRoot);
    public void deleteInstanceEvents(BaseAggregateRoot aggregateRoot);

}
