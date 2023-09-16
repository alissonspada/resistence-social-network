package org.example.rules;

import org.example.model.Item;
import org.example.repositories.InventoryRepository;
import org.example.repositories.RebelRepository;

import java.util.*;

public class TradeRules {
    private final InventoryRepository inventoryRepo;
    private final RebelRepository rebelRepo;

    public TradeRules(InventoryRepository inventoryRepo, RebelRepository rebelRepo) {
        this.inventoryRepo = inventoryRepo;
        this.rebelRepo = rebelRepo;
    }
    public Item getDefaultItem() { return new Item("default", 0, 0); }
    public List<Item> check(UUID sourceId, Item sourceTrade, UUID targetId, Item targetTrade) throws TradeFailureException
    {
        if (rebelRepo.findById(sourceId).orElseThrow().getReportCounter() >= 3)
            throw new TradeFailureException("source traitor");
        if (rebelRepo.findById(targetId).orElseThrow().getReportCounter() >= 3)
            throw new TradeFailureException("target traitor");

        Item sourceItem = inventoryRepo.findItemByName(sourceId, sourceTrade.getName()).orElseGet(this::getDefaultItem);
        Item targetItem = inventoryRepo.findItemByName(targetId, targetTrade.getName()).orElseGet(this::getDefaultItem);
        if (sourceTrade.quantity * sourceTrade.price != targetTrade.quantity * targetTrade.price) throw new TradeFailureException("points do not match");

        if (sourceItem.quantity < sourceTrade.quantity) {
            throw new TradeFailureException("insufficient funds source");
        } else if (targetItem.quantity < targetTrade.quantity) {
            throw new TradeFailureException("insufficient funds target");
        }

        return new ArrayList<>(Arrays.asList(sourceItem, targetItem));


    }
}