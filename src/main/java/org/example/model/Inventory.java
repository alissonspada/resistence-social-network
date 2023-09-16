package org.example.model;

import org.example.repositories.GenericEntity;

import java.util.List;
import java.util.UUID;

public class Inventory extends GenericEntity {
    UUID id;
    List<Item> invList;

    public Inventory(List<Item> invList) {
        this.id = UUID.randomUUID();
        this.invList = invList;
    }

    public List<Item> getInvList() {
        return invList;
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
