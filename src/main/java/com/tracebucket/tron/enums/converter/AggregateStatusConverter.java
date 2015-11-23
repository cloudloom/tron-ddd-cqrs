package com.tracebucket.tron.enums.converter;

import com.tracebucket.tron.ddd.domain.BaseAggregateRoot;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by sadath on 23-Nov-2015.
 */
@Converter(autoApply = true)
public class AggregateStatusConverter implements AttributeConverter<BaseAggregateRoot.AggregateStatus, String>{
    @Override
    public String convertToDatabaseColumn(BaseAggregateRoot.AggregateStatus aggregateStatus) {
        switch (aggregateStatus) {
            case ACTIVE:
                return "ACTIVE";
            case ARCHIVE:
                return "ARCHIVE";
            default:
                throw new IllegalArgumentException("Unknown value: " + aggregateStatus);
        }
    }

    @Override
    public BaseAggregateRoot.AggregateStatus convertToEntityAttribute(String s) {
        switch (s) {
            case "ACTIVE":
                return BaseAggregateRoot.AggregateStatus.ACTIVE;
            case "ARCHIVE":
                return BaseAggregateRoot.AggregateStatus.ARCHIVE;
            default:
                throw new IllegalArgumentException("Unknown value: " + s);
        }
    }
}