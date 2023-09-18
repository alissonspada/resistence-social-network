package org.example.model;

import org.example.repositories.GenericEntity;

import java.util.List;
import java.util.UUID;

public class Inventory extends GenericEntity {
    private UUID id;
    private UUID ownerId;
    private List<Item> itemList;

    public Inventory(UUID ownerId, List<Item> itemList) {
        this.ownerId = ownerId;
        this.id = UUID.randomUUID();
        this.itemList = itemList;
    }
    public Inventory() {}

    public List<Item> getItemList() {
        return itemList;
    }

    public void setNewItemList(List<Item> newItemList) {
        itemList = newItemList;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID newUUID) {
        id = newUUID;
    }
    @Override
    public String toString() {
        return "Inventory{" + "id=" + id + ", invList=" + itemList + '}';
    }
}
