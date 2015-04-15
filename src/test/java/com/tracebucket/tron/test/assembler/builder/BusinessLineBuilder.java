package com.tracebucket.tron.test.assembler.builder;

import com.tracebucket.tron.test.assembler.sample.BusinessLine;

/**
 * Created by sadath on 25-Nov-14.
 */
public class BusinessLineBuilder {
    private String name;
    private String description;

    public BusinessLineBuilder(){ }

    public static BusinessLineBuilder aBusinessLine(){
        return new BusinessLineBuilder();
    }

    public BusinessLineBuilder withName(String name){
        this.name = name;
        return this;
    }

    public BusinessLineBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public BusinessLine build(){
        BusinessLine businessLine = new BusinessLine();
        businessLine.setName(name);
        businessLine.setDescription(description);
        return businessLine;
    }
}
