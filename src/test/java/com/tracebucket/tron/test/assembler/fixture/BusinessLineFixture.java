package com.tracebucket.tron.test.assembler.fixture;

import com.tracebucket.tron.test.assembler.builder.BusinessLineBuilder;
import com.tracebucket.tron.test.assembler.sample.BusinessLine;

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
