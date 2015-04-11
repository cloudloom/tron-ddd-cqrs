package com.tracebucket.tron.fixture;

import com.tracebucket.tron.builder.BusinessLineBuilder;
import com.tracebucket.tron.domain.BusinessLine;

/**
 * Created by sadath on 25-Nov-14.
 */
public class BusinessLineFixture {
    public static BusinessLine standardBusinessLine(){
        BusinessLine businessLine = BusinessLineBuilder.aBusinessLine()
                .withName("Business Line1")
                .withDescription("Business line description")
                .build();
        return businessLine;
    }
}
