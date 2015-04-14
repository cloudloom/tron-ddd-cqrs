package com.tracebucket.tron.test.assembler.sample;

import com.tracebucket.tron.assembler.BaseResource;

/**
 * Created by sadath on 31-Mar-15.
 */
public class BusinessLineResource extends BaseResource {
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}