package org.example.model;

public class Item {
    private final String name;
    private Integer quantity;
    private final Integer price;

    public Item(String name, Integer quantity, Integer price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "InventoryItem{" + "name='" + name + '\'' + ", quantity=" + quantity + ", price=" + price + '}';
    }
}
