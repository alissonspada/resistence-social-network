package org.example.model;

public class Item {
    String name;
    private Integer quantity;
    private Integer price;

    public Item(String name, Integer quantity, Integer price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
