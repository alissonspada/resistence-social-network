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
    private UUID id = UUID.randomUUID();
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
