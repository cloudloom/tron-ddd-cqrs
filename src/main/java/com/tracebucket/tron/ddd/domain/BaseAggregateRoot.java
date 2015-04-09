package com.tracebucket.tron.ddd.domain;

import com.tracebucket.tron.ddd.exception.DomainOperationException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author ffl
 * @since 13-01-2015
 * @version 0.1
 */

@Component
@Scope("prototype")//Wired to the spring container in the Entity Liatener.
@MappedSuperclass
@EntityListeners(AggregateRootListener.class)
public abstract class  BaseAggregateRoot {
	public static enum AggregateStatus {
		ACTIVE, ARCHIVE
	}

	@EmbeddedId
	@AttributeOverrides({
		  @AttributeOverride(name = "aggregateId", column = @Column(name = "ID", nullable = false))})
	protected AggregateId aggregateId;



	@Version
	private Long version;

	@Enumerated(EnumType.ORDINAL)
	private AggregateStatus aggregateStatus = AggregateStatus.ACTIVE;

    @Transient
    private final String _instanceId;

    @Column(name = "PASSIVE", nullable = false, columnDefinition = "boolean default false")
    @Basic(fetch = FetchType.EAGER)
    private boolean passive;

    public BaseAggregateRoot(){
        aggregateId = AggregateId.generate();
        _instanceId = UUID.randomUUID().toString();
    }
	
	public void markAsRemoved() {
		aggregateStatus = AggregateStatus.ARCHIVE;
	}

	public AggregateId getAggregateId() {
		return aggregateId;
	}

	public boolean isRemoved() {
		return aggregateStatus == AggregateStatus.ARCHIVE;
	}
	
	protected void domainError(String message) {
		throw new DomainOperationException(aggregateId, message);
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof BaseAggregateRoot) {
			BaseAggregateRoot other = (BaseAggregateRoot) obj;
			if (other.aggregateId == null)
				return false;
			return other.aggregateId.equals(aggregateId);
		}
		
		return false;
	}

    @Override
	public int hashCode() {	
		return aggregateId.hashCode();
	}

    public String instanceId(){
        return _instanceId;
    }

    public boolean isPassive() {
        return passive;
    }

    public void setPassive(boolean passive) {
        this.passive = passive;
    }

    public void setAggregateId(AggregateId aggregateId) {
        this.aggregateId = aggregateId;
    }
}
