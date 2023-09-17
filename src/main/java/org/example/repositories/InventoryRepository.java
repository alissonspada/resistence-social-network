package org.example.repositories;

import org.example.model.Inventory;
import org.example.model.Item;

import java.util.Optional;
import java.util.UUID;

public class InventoryRepository extends AbstractRepository<Inventory> {
    public Optional<Item> findItemByName(UUID id, String itemName) {
        if (findById(id).isPresent())
            return findById(id).get().getItemList().stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst();
        else return Optional.empty();
    }
}