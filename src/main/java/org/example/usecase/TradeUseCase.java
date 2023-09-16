package org.example.usecase;

import org.example.model.Item;
import org.example.repositories.InventoryRepository;
import org.example.repositories.RebelRepository;
import org.example.rules.TradeFailureException;
import org.example.rules.TradeRules;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TradeUseCase {
    private final InventoryRepository inventoryRepository;
    private final RebelRepository rebelRepository;
    public TradeUseCase(InventoryRepository inventoryRepository, RebelRepository rebelRepository) {
        this.inventoryRepository = inventoryRepository;
        this.rebelRepository = rebelRepository;
    }

    public void trade(UUID sourceId, Item sourceTrade, UUID targetId, Item targetTrade) throws TradeFailureException {
        TradeRules tradeRules = new TradeRules(inventoryRepository, rebelRepository);
        List<Item> items = tradeRules.check(sourceId, sourceTrade, targetId, targetTrade);

        Item sourceItem = items.get(0);
        Item targetItem = items.get(1);

        sourceItem.setQuantity(sourceItem.quantity - sourceTrade.quantity);
        targetItem.setQuantity(targetItem.quantity - targetTrade.quantity);

        List<Item> sourceList =  inventoryRepository.findById(sourceId).get().getInvList();
        List<Item> targetList = inventoryRepository.findById(targetId).get().getInvList();

        Optional<Item> sameSourceItem = inventoryRepository.findItemByName(sourceId, targetTrade.getName());
        if (sameSourceItem.isPresent()) sameSourceItem.get().setQuantity(sameSourceItem.get().quantity + targetTrade.quantity);
        else sourceList.add(targetTrade);

        Optional<Item> sameTargetItem = inventoryRepository.findItemByName(targetId, sourceTrade.getName());
        if (sameTargetItem.isPresent()) sameTargetItem.get().setQuantity(sameTargetItem.get().quantity + sourceTrade.quantity);
        else targetList.add(sourceTrade);
    }
}