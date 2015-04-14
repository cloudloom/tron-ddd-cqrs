package com.tracebucket.tron.repository.jpa;

import com.tracebucket.tron.ddd.domain.AggregateId;
import com.tracebucket.tron.ddd.jpa.BaseAggregateRepository;
import com.tracebucket.tron.domain.Organization;

/**
 * Created by sadath on 13-Jan-15.
 */
public interface OrganizationRepository extends BaseAggregateRepository<Organization, AggregateId> {
}