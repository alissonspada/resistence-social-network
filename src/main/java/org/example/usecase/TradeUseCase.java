package org.example.usecase;

import org.example.model.Item;
import org.example.repositories.InventoryRepository;
import org.example.repositories.RebelRepository;
import org.example.rules.TradeFailureException;
import org.example.rules.TradeRules;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class TradeUseCase {
    private final InventoryRepository inventoryRepository;
    private final RebelRepository rebelRepository;
    public TradeUseCase(InventoryRepository inventoryRepository, RebelRepository rebelRepository) {
        this.inventoryRepository = inventoryRepository;
        this.rebelRepository = rebelRepository;
    }

    public void trade(UUID sourceId, Item sourceTradeItem, UUID targetId, Item targetTradeItem) throws TradeFailureException {
        List<Item> checkedTradeItems = new TradeRules(inventoryRepository, rebelRepository)
                .check(sourceId, sourceTradeItem, targetId, targetTradeItem);

        Item sourceInventoryItem = checkedTradeItems.get(0);
        Item targetInventoryItem = checkedTradeItems.get(1);

        sourceInventoryItem.setQuantity( sourceInventoryItem.getQuantity() - sourceTradeItem.getQuantity() );
        targetInventoryItem.setQuantity( targetInventoryItem.getQuantity() - targetTradeItem.getQuantity() );

        trySetElseAdd( sourceId, targetTradeItem, inventoryRepository );
        trySetElseAdd( targetId, sourceTradeItem, inventoryRepository );
    }


    private void trySetElseAdd(UUID id, Item sameNameTradeItem, InventoryRepository invRepo) {
        List<Item> invList = new ArrayList<>();
        try {
            invList = invRepo.findById(id).orElseThrow().getInvList();
            Item sameName = invList.stream().filter(i -> i.getName().equals( sameNameTradeItem.getName() )
            ).findFirst().orElseThrow();

            /* Try setting quantity to item if present */
            sameName.setQuantity(sameName.getQuantity() + sameNameTradeItem.getQuantity());
        } catch (NoSuchElementException ignored) {

            /* Default: just add to list */
            invList.add(sameNameTradeItem);
        }
    }
}