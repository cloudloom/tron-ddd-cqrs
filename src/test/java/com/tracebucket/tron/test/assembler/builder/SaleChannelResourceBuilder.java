package com.tracebucket.tron.test.assembler.builder;

import com.tracebucket.tron.test.assembler.sample.SaleChannelResource;

/**
 * Created by sadath on 14-Apr-15.
 */
public class SaleChannelResourceBuilder {
    private String name;
    private String description;

    private SaleChannelResourceBuilder(){ }

    public static SaleChannelResourceBuilder aSaleChannelResourceBuilder(){
        return new SaleChannelResourceBuilder();
    }

    public SaleChannelResourceBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public SaleChannelResourceBuilder withName(String name){
        this.name = name;
        return this;
    }

    public SaleChannelResource build(){
        SaleChannelResource saleChannel = new SaleChannelResource();
        saleChannel.setName(name);
        saleChannel.setDescription(description);
        return saleChannel;
    }
}
