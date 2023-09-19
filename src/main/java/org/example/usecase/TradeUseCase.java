package org.example.usecase;

import org.example.model.Item;
import org.example.repositories.InventoryRepository;
import org.example.repositories.RebelRepository;
import org.example.rules.TradeFailureException;
import org.example.rules.TradeRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeUseCase {
    private final InventoryRepository inventoryRepo;
    private final RebelRepository rebelRepo;

    @Autowired
    public TradeUseCase(InventoryRepository inventoryRepo, RebelRepository rebelRepo) {
        this.inventoryRepo = inventoryRepo;
        this.rebelRepo = rebelRepo;
    }

    public void handle(Integer sourceInventoryId, Item sourceTradeItem, Integer targetInventoryId, Item targetTradeItem) throws TradeFailureException {
        new TradeRules(inventoryRepo, rebelRepo).check(sourceInventoryId, sourceTradeItem, targetInventoryId, targetTradeItem);

        Item sourceInventoryItem = inventoryRepo.findItemByName(sourceInventoryId, sourceTradeItem.getName()).get();
        Item targetInventoryItem = inventoryRepo.findItemByName(targetInventoryId, targetTradeItem.getName()).get();

        inventoryRepo.removeQuantity(sourceInventoryId, sourceTradeItem.getName(), sourceTradeItem.getQuantity());
        inventoryRepo.removeQuantity(targetInventoryId, targetTradeItem.getName(), targetTradeItem.getQuantity());
        inventoryRepo.addQuantity(sourceInventoryId, sourceTradeItem);
        inventoryRepo.addQuantity(targetInventoryId, targetTradeItem);
    }
}
