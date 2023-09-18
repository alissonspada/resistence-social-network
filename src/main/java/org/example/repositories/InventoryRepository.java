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

    public void exchangeItems(Integer sourceInventoryId, Integer targetInventoryId, Item sourceItem, Item targetItem) {
       if (findById(sourceInventoryId).isPresent() && findById(targetInventoryId).isPresent()){
           Inventory sourceInventory = findById(sourceInventoryId).get();
           Inventory targetInventory = findById(targetInventoryId).get();

           Item sameNameSourceItem = sourceInventory.getItemList().stream()
                   .filter(item -> item.getName().equals(sourceItem.getName())).findFirst().get();
           Item sameNameTargetItem = targetInventory.getItemList().stream()
                   .filter(item -> item.getName().equals(targetItem.getName())).findFirst().get();

           

       }
    }
}