package com.tracebucket.tron.test.assembler.builder;

import com.tracebucket.tron.test.assembler.sample.BusinessLineResource;

/**
 * Created by sadath on 31-Mar-15.
 */
public class BusinessLineResourceBuilder {
    private String name;
    private String description;

    public BusinessLineResourceBuilder(){ }

    public static BusinessLineResourceBuilder aBusinessLineResource(){
        return new BusinessLineResourceBuilder();
    }

    public BusinessLineResourceBuilder withName(String name){
        this.name = name;
        return this;
    }

    public BusinessLineResourceBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public BusinessLineResource build(){
        BusinessLineResource businessLine = new BusinessLineResource();
        businessLine.setName(name);
        businessLine.setDescription(description);
        return businessLine;
    }
}