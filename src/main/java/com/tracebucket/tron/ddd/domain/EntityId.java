package com.tracebucket.tron.ddd.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;


/**
 * @author ssm
 * @since 13-02-2015
 * @version 0.1
 */

@SuppressWarnings("serial")
@Embeddable
public class EntityId implements Serializable {
    private String entityId;

    public EntityId(String entityId) {
        org.apache.commons.lang3.Validate.notNull(entityId);
        this.entityId = entityId;
    }

    protected EntityId() {
    }

    public static EntityId generate(){
        return new EntityId(UUID.randomUUID().toString());
    }

    public String getId() {
        return entityId;
    }

    @Override
    public int hashCode() {
        return entityId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EntityId other = (EntityId) obj;
        if (entityId == null) {
            if (other.entityId != null)
                return false;
        } else if (!entityId.equals(other.entityId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return entityId;
    }
}
