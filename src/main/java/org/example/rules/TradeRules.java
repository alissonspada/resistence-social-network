package org.example.rules;

import org.example.model.Inventory;
import org.example.model.Item;
import org.example.model.Rebel;
import org.example.repositories.InventoryRepository;
import org.example.repositories.RebelRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TradeRules {
    private final InventoryRepository inventoryRepo;
    private final RebelRepository rebelRepo;

    public TradeRules(InventoryRepository inventoryRepo, RebelRepository rebelRepo) {
        this.inventoryRepo = inventoryRepo;
        this.rebelRepo = rebelRepo;
    }
    public List<Item> check(Integer sourceInventoryId, Item sourceTrade, Integer targetInventoryId, Item targetTrade) throws TradeFailureException {
        Inventory sourceInventory = inventoryRepo.findById(sourceInventoryId).orElseThrow(
                () -> new TradeFailureException("source inventory not found")
        );
        Inventory targetInventory = inventoryRepo.findById(targetInventoryId).orElseThrow(
                () -> new TradeFailureException("target inventory not found")
        );

        rebelRepo.findById(sourceInventory.getOwnerId()).filter(Rebel::isNotTraitor).orElseThrow(
                () -> new TradeFailureException("source rebel is either a traitor or could not be found")
        );
        rebelRepo.findById(targetInventory.getOwnerId()).filter(Rebel::isNotTraitor).orElseThrow(
                () -> new TradeFailureException("target rebel is either a traitor or could not be found")
        );

        Item sourceItem = inventoryRepo.findItemByName(sourceInventoryId, sourceTrade.getName()).orElseThrow(
                () -> new TradeFailureException("no such item source")
        );
        Item targetItem = inventoryRepo.findItemByName(targetInventoryId, targetTrade.getName()).orElseThrow(
                () -> new TradeFailureException("no such item target")
        );

        if (sourceTrade.getQuantity() * sourceItem.getPrice() != targetTrade.getQuantity() * targetItem.getPrice())
            throw new TradeFailureException("points do not match");

        if (sourceItem.getQuantity() < sourceTrade.getQuantity()) throw new TradeFailureException("insufficient funds source");
        else if (targetItem.getQuantity() < targetTrade.getQuantity()) throw new TradeFailureException("insufficient funds target");

        return new ArrayList<>(Arrays.asList(sourceItem, targetItem));
    }
}