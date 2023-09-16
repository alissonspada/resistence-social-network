package org.example.rules;

import org.example.model.Item;
import org.example.model.Rebel;
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
    public List<Item> check(UUID sourceId, Item sourceTrade, UUID targetId, Item targetTrade) throws TradeFailureException
    {
        UUID ownerSourceId = inventoryRepo.findById(sourceId).orElseThrow().getOwnerId();
        UUID ownerTargetId = inventoryRepo.findById(targetId).orElseThrow().getOwnerId();

        rebelRepo.findById(ownerSourceId).filter(Rebel::isNotTraitor).orElseThrow(
                () -> new TradeFailureException("source is either a traitor or could not be found")
        );
        rebelRepo.findById(ownerTargetId).filter(Rebel::isNotTraitor).orElseThrow(
                () -> new TradeFailureException("target is either a traitor or could not be found")
        );
        Item sourceItem = inventoryRepo.findItemByName(sourceId, sourceTrade.getName()).orElseThrow(
                () -> new NoSuchElementException("no such item source")
        );
        Item targetItem = inventoryRepo.findItemByName(targetId, targetTrade.getName()).orElseThrow(
                () -> new NoSuchElementException("no such item target")
        );

        if (sourceTrade.getQuantity() * sourceTrade.getPrice() != targetTrade.getQuantity() * targetTrade.getPrice())
            throw new TradeFailureException("points do not match");

        if (sourceItem.getQuantity() < sourceTrade.getQuantity()) {
            throw new TradeFailureException("insufficient funds source");
        } else if (targetItem.getQuantity() < targetTrade.getQuantity()) {
            throw new TradeFailureException("insufficient funds target");
        }

        return new ArrayList<>(Arrays.asList(sourceItem, targetItem));
    }
}