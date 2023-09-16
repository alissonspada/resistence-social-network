package org.example.model;

import org.example.repositories.GenericEntity;

import java.util.List;
import java.util.UUID;

public class Inventory extends GenericEntity {
    private final UUID id;
    private final UUID ownerId;
    private List<Item> invList;

    public Inventory(UUID ownerId, List<Item> invList) {
        this.ownerId = ownerId;
        this.id = UUID.randomUUID();
        this.invList = invList;
    }

    public List<Item> getInvList() {
        return invList;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setInvList(List<Item> invList) {
        this.invList = invList;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Inv{" + "id=" + id + ", invList=" + invList + '}';
    }
}
