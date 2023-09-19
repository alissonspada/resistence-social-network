package org.example.repositories;

import org.example.model.Inventory;
import org.example.model.Item;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;
@Repository
public class InventoryRepository extends AbstractRepository<Inventory> {
    public Optional<Item> findItemByName(Integer id, String itemName) {
        if (findById(id).isPresent())
            return findById(id).get().getItemList().stream()
                .filter(item -> item.getName().equals(itemName))
                .findFirst();
        else return Optional.empty();
    }

    public void addQuantity(Integer inventoryId, Item tradeItem) {
        if (findById(inventoryId).isPresent()) {
            Inventory inventory = findById(inventoryId).get();
            try {
                Item sameNameItem = inventory.getItemList()
                        .stream()
                        .filter(item -> item.getName().equals(tradeItem.getName()))
                        .findFirst()
                        .orElseThrow();
                int currentQuantity = sameNameItem.getQuantity();
                int extraQuantity = tradeItem.getQuantity();
                sameNameItem.setQuantity(currentQuantity + extraQuantity);
            } catch (Exception e) {
                inventory.getItemList().add(tradeItem);
            }
        }
    }

    public void removeQuantity(Integer inventoryId, String itemName, int toRemoveQuantity) {
       if (findById(inventoryId).isPresent()) {
           try {
               Item existentItem = findById(inventoryId)
                       .get()
                       .getItemList()
                       .stream()
                       .filter(item -> item.getName().equals(itemName))
                       .findFirst()
                       .get();
               int currentQuantity = existentItem.getQuantity();
               existentItem.setQuantity(currentQuantity - toRemoveQuantity);
           } catch (NoSuchElementException ignored) {}
       }
    }
}