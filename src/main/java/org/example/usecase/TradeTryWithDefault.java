package org.example.usecase;

import org.example.model.Item;
import org.example.repositories.InventoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class TradeTryWithDefault {
    public void trySetElseAdd(UUID destinationInvId, Item sameNameTradeItem, int secondItemQuantity, InventoryRepository invRepo) {
        List<Item> firstInvList = new ArrayList<>();
        try {
            firstInvList = invRepo.findById(destinationInvId).orElseThrow().getItemList();
            Item sameName = firstInvList
                    .stream()
                    .filter(i -> i.getName().equals(sameNameTradeItem.getName()))
                    .findFirst()
                    .orElseThrow();

            /* Try setting quantity to item if present */
            sameName.setQuantity(sameName.getQuantity() + sameNameTradeItem.getQuantity());
        } catch (NoSuchElementException ignored) {
            /* Default: just add to list */
            sameNameTradeItem.setQuantity(secondItemQuantity);
            firstInvList.add(sameNameTradeItem);
        }
    }
}
