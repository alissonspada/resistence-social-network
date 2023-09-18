package org.example.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.example.repositories.GenericEntity;

import java.util.List;
import java.util.UUID;

@Entity
public class Inventory extends GenericEntity {
    @Id
    private UUID uuid = UUID.randomUUID();
    private UUID ownerId;
    @ElementCollection
    private List<Item> itemList;

    public Inventory(UUID ownerId, List<Item> itemList) {
        this.ownerId = ownerId;
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
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID newUUID) {
        uuid = newUUID;
    }

    @Override
    public String toString() {
        return "Inventory { " + "itemList=" + itemList + ", UUID=" + uuid + '}';
    }
}
