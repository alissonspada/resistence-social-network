package org.example.usecase;

import org.example.model.Inventory;
import org.example.model.Item;
import org.example.repositories.InventoryRepository;
import org.example.repositories.RebelRepository;
import org.example.rules.TradeFailureException;
import org.example.rules.TradeRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        List<Item> checkedTradeItems = new TradeRules(inventoryRepo, rebelRepo)
                .check(sourceInventoryId, sourceTradeItem, targetInventoryId, targetTradeItem);

        Item sourceInventoryItem = checkedTradeItems.get(0);
        Item targetInventoryItem = checkedTradeItems.get(1);

        sourceInventoryItem.setQuantity( sourceInventoryItem.getQuantity() - sourceTradeItem.getQuantity() );
        targetInventoryItem.setQuantity( targetInventoryItem.getQuantity() - targetTradeItem.getQuantity() );

        TradeTryWithDefault tryWithDefault = new TradeTryWithDefault();
        tryWithDefault.trySetElseAdd( sourceInventory, targetTradeItem);
        tryWithDefault.trySetElseAdd( targetInventory, sourceTradeItem);
    }
}
