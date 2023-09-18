package org.example.repositories;

import org.example.model.Inventory;
import org.example.model.Item;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class InventoryRepository extends AbstractRepository<Inventory> {
    public Optional<Item> findItemByName(Integer id, String itemName) {
        if (findById(id).isPresent())
            return findById(id).get().getItemList().stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst();
        else return Optional.empty();
    }
}