package org.example.repositories;

import java.util.UUID;

public abstract class GenericEntity {
    public abstract UUID getId();

    public <T extends GenericEntity> T getEntity(Class<T> entityType) {
        if (entityType.isInstance(this)) {
            return entityType.cast(this);
        } else {
            throw new ClassCastException("Cannot cast to " + entityType.getName());
        }
    }

}
