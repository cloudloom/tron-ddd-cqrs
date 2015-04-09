package com.tracebucket.tron.ddd.exception;

import com.tracebucket.tron.ddd.domain.AggregateId;

/**
 * @author ffl
 * @since 13-01-2015
 * @version 0.1
 */

@SuppressWarnings("serial")
public class DomainOperationException extends RuntimeException{

	private AggregateId aggregateId;

	public DomainOperationException(AggregateId aggregateId, String message){
		super(message);
		this.aggregateId = aggregateId;
	}
	
	public AggregateId getAggregateId() {
		return aggregateId;
	}
}
