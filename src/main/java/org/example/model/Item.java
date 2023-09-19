package org.example.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Item {
    private Integer id;
    private String name;
    private Integer price;
    private Integer quantity;

    public Item(String name, Integer quantity, Integer price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public Item(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    public Item() {}

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item { " + "name=" + name + ", quantity=" + quantity + ", price=" + price + "}";
    }
}
