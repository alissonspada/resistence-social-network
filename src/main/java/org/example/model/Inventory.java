package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Inventory {
    @Id
    private Integer id;
    @OneToMany
    @JoinColumn(name = "item_list_id")
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Inventory { " + "itemList=" + itemList + ", ID=" + id + '}';
    }
}
