package org.example.usecase;

import org.example.model.Inventory;
import org.example.model.Item;
import org.example.repositories.InventoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class TradeTryWithDefault {
    public void trySetElseAdd(Inventory destinationInventory, Item sameNameSourceItem, Item sameNameTargetItem) {

        try {
            Item sameName = destinationInventory.getItemList()
                    .stream()
                    .filter(i -> i.getName().equals(sameNameTargetItem.getName()))
                    .findFirst()
                    .orElseThrow();

            /* Try setting quantity to item if present */
            sameName.setQuantity(sameName.getQuantity() + sameNameTargetItem.getQuantity());
        } catch (NoSuchElementException ignored) {
            /* Default: just add to list */
            destinationInventory.getItemList().add(sameNameTargetItem);
        } finally {
            sameNameSourceItem.setQuantity();
            destinationInventory.getItemList()
        }
    }
}

