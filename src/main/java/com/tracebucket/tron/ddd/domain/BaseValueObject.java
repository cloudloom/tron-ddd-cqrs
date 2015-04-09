package com.tracebucket.tron.ddd.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author ssm
 * @since 28-01-2015
 * @version 0.1
 */

@MappedSuperclass
public abstract class BaseValueObject {

    @Column(name = "PASSIVE", nullable = false, columnDefinition = "boolean default false")
    private boolean passive;

    public boolean isPassive() {
        return passive;
    }

    public void setPassive(boolean passive) {
        this.passive = passive;
    }
}
