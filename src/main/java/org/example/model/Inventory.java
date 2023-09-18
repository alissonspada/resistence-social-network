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
    private Integer id;
    private Integer ownerId;
    @ElementCollection
    private List<Item> itemList;

    public Inventory(List<Item> itemList) {
        this.itemList = itemList;
    }
    public Inventory() {}

    public List<Item> getItemList() {
        return itemList;
    }

    public void setNewItemList(List<Item> newItemList) {
        itemList = newItemList;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public Integer getEntityId() {
        return id;
    }

    @Override
    public void setEntityId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Inventory { " + "itemList=" + itemList + ", ID=" + id + '}';
    }
}
