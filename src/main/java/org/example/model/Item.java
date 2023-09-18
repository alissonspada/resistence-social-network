package org.example.model;

public class Item {
    private final String name;
    private final Integer price;
    private Integer quantity;

    public Item(String name, Integer quantity, Integer price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

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
        return "InventoryItem{" + "name='" + name + '\'' + ", quantity=" + quantity + ", price=" + price + '}';
    }
}
