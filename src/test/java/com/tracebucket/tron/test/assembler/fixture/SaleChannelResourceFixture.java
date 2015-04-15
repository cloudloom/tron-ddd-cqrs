package com.tracebucket.tron.test.assembler.fixture;

import com.tracebucket.tron.test.assembler.builder.SaleChannelResourceBuilder;
import com.tracebucket.tron.test.assembler.sample.SaleChannelResource;

import java.util.Date;

/**
 * Created by sadath on 14-Apr-15.
 */
public class SaleChannelResourceFixture {
    public static SaleChannelResource standardSaleChannel(){
        SaleChannelResource saleChannel = SaleChannelResourceBuilder.aSaleChannelResourceBuilder()
                .withName("Sale Channel " + new Date().getTime())
                .withDescription("Sale Channel " + new Date().getTime())
                .build();
        return saleChannel;
    }
}
