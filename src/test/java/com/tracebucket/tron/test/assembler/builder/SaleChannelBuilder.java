package com.tracebucket.tron.test.assembler.builder;

import com.tracebucket.tron.ddd.domain.EntityId;
import com.tracebucket.tron.test.assembler.sample.SaleChannel;

import java.util.UUID;

/**
 * Created by sadath on 14-Apr-15.
 */
public class SaleChannelBuilder {
    private String name;
    private String description;

    private SaleChannelBuilder(){ }

    public static SaleChannelBuilder aSaleChannelBuilder(){
        return new SaleChannelBuilder();
    }

    public SaleChannelBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public SaleChannelBuilder withName(String name){
        this.name = name;
        return this;
    }

    public SaleChannel build(){
        SaleChannel saleChannel = new SaleChannel();
        saleChannel.setName(name);
        saleChannel.setDescription(description);
        saleChannel.setEntityId(new EntityId(UUID.randomUUID().toString()));
        return saleChannel;
    }
}
