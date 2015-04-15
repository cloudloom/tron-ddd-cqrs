package com.tracebucket.tron.test.assembler.fixture;

import com.tracebucket.tron.test.assembler.builder.BusinessLineResourceBuilder;
import com.tracebucket.tron.test.assembler.sample.BusinessLineResource;

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
