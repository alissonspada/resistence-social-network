package org.example.repositories;

import java.util.UUID;

public abstract class GenericEntity {
    private final UUID genericUUID = UUID.randomUUID();
    public UUID getGenericUUID() { return genericUUID; }
    public abstract UUID getEntityUUID();
}
