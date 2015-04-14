package com.tracebucket.tron.rest.resource;


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