package com.tracebucket.tron.ddd.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author ffl
 * @since 29-01-2015
 * @version 0.1
 */

@SuppressWarnings("serial")
@Embeddable
public class AggregateId implements Serializable{

	private String aggregateId;

	public AggregateId(String aggregateId) {
		org.apache.commons.lang3.Validate.notNull(aggregateId);
		this.aggregateId = aggregateId;
	}

    protected AggregateId() {
	}
	
	public static AggregateId generate(){
		return new AggregateId(UUID.randomUUID().toString());
	}

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    @Override
	public int hashCode() {
		return aggregateId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AggregateId other = (AggregateId) obj;
		if (aggregateId == null) {
			if (other.aggregateId != null)
				return false;
		} else if (!aggregateId.equals(other.aggregateId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return aggregateId;
	}
}
