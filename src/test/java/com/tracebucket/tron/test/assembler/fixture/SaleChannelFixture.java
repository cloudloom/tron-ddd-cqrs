package com.tracebucket.tron.test.assembler.fixture;

import com.tracebucket.tron.test.assembler.builder.SaleChannelBuilder;
import com.tracebucket.tron.test.assembler.sample.SaleChannel;

import java.util.Date;

/**
 * Created by sadath on 14-Apr-15.
 */
public class SaleChannelFixture {
    public static SaleChannel standardSaleChannel(){
        SaleChannel saleChannel = SaleChannelBuilder.aSaleChannelBuilder()
                .withName("Sale Channel " + new Date().getTime())
                .withDescription("Sale Channel " + new Date().getTime())
                .build();
        return saleChannel;
    }
}
