package com.tracebucket.tron.ddd.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ffl
 * @since 30-01-2015
 * @version 0.1
 */

public class EventModel implements Serializable{
    private final int identifier;
    private final String instance;
    private final String event;
    private final BaseAggregateRoot aggregateRoot;
    private final Date timestamp;

    public EventModel(BaseAggregateRoot aggregateRoot, String event){
        this.instance = aggregateRoot.instanceId();
        this.identifier = aggregateRoot.hashCode();
        this.event = event;
        this.aggregateRoot = aggregateRoot;
        this.timestamp = new Date();
    }

    public int getIdentifier() {
        return identifier;
    }

    public BaseAggregateRoot getAggregateRoot() {
        return aggregateRoot;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getEvent() {
        return event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventModel that = (EventModel) o;

        if (identifier != that.identifier) return false;
        if (!timestamp.equals(that.timestamp)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = identifier;
        result = 31 * result + timestamp.hashCode();
        return result;
    }
}
