package com.tracebucket.tron.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * User: ffl
 * Date: 8/11/13
 * Time: 2:50 PM
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseResource implements Serializable {

    protected String uid;

    protected boolean passive;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isPassive() {
        return passive;
    }

    public void setPassive(boolean passive) {
        this.passive = passive;
    }

    @Override
    public String toString() {
        return "BaseResource{" +
                "uid='" + uid + '\'' +
                ", status=" + passive +
                "} " + super.toString();
    }
}
