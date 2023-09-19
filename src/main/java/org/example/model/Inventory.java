package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Inventory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "rebel_id")
    private Rebel rebel;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_list_id")
    private List<Item> itemList;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

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
