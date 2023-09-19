package org.example.usecase;

import org.example.model.Item;
import org.example.repositories.InventoryRepository;
import org.example.rules.TradeFailureException;
import org.example.rules.TradeRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeUseCase {
    private final InventoryRepository inventoryRepo;
    private final TradeRules tradeRules;

    @Autowired
    public TradeUseCase(InventoryRepository inventoryRepo, TradeRules tradeRules) {
        this.inventoryRepo = inventoryRepo;
        this.tradeRules = tradeRules;
    }

    public void handle(Integer sourceInventoryId, Item sourceTradeItem, Integer targetInventoryId, Item targetTradeItem) throws TradeFailureException {
        tradeRules.check(sourceInventoryId, sourceTradeItem, targetInventoryId, targetTradeItem);

        inventoryRepo.removeQuantity(sourceInventoryId, sourceTradeItem.getName(), sourceTradeItem.getQuantity());
        inventoryRepo.removeQuantity(targetInventoryId, targetTradeItem.getName(), targetTradeItem.getQuantity());
        inventoryRepo.addQuantity(sourceInventoryId, targetTradeItem);
        inventoryRepo.addQuantity(targetInventoryId, sourceTradeItem);
    }
}
