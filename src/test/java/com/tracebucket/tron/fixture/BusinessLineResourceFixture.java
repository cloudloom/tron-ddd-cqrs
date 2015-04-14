package com.tracebucket.tron.fixture;

import com.tracebucket.tron.builder.BusinessLineResourceBuilder;
import com.tracebucket.tron.rest.resource.BusinessLineResource;

/**
 * Created by sadath on 31-Mar-15.
 */
public class BusinessLineResourceFixture {
    public static BusinessLineResource standardBusinessLine(){
        BusinessLineResource businessLine = BusinessLineResourceBuilder.aBusinessLineResource()
                .withName("Business Line1")
                .withDescription("Business line description")
                .build();
        return businessLine;
    }
}
