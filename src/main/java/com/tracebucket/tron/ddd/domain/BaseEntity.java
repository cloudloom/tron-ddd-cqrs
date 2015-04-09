package com.tracebucket.tron.ddd.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ffl
 * @since 13-01-2015
 * @version 0.1
 */

@MappedSuperclass
public abstract class BaseEntity implements Serializable{

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "entityId", column = @Column(name = "ID", nullable = false))})
    protected EntityId entityId;

    @Column(name = "PASSIVE", nullable = false, columnDefinition = "boolean default false")
    @Basic(fetch = FetchType.EAGER)
    private boolean passive;

    public BaseEntity(){
        entityId = EntityId.generate();
    }

    public boolean isPassive() {
        return passive;
    }

    public void setPassive(boolean passive) {
        this.passive = passive;
    }

    public EntityId getEntityId() {
        return entityId;
    }

    public void setEntityId(EntityId entityId) {
        this.entityId = entityId;
    }

    public Object getId() {
        return entityId;
    }
}
