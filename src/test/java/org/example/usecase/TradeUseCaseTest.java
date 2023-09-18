package org.example.usecase;

import org.example.model.Inventory;
import org.example.model.Item;
import org.example.model.Rebel;
import org.example.repositories.InventoryRepository;
import org.example.repositories.RebelRepository;
import org.example.rules.TradeFailureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TradeUseCaseTest {
    private final RebelRepository rebelRepository = new RebelRepository();
    private final InventoryRepository inventoryRepository = new InventoryRepository();
    private final Rebel luke = new Rebel("luke", 18, "male");
    private final Rebel leia = new Rebel("leia", 30, "female");
    private final Inventory lukeInv = new Inventory(luke.getEntityUUID(),
            new ArrayList<>( List.of( new Item("doritos", 2, 1)) )
    );
    private final Inventory leiaInv = new Inventory(leia.getEntityUUID(),
            new ArrayList<>( List.of( new Item("water", 1, 2)) )
    );
    private final TradeUseCase tradeUseCase = new TradeUseCase(inventoryRepository, rebelRepository);

    @BeforeEach
    void setUp() {
        rebelRepository.deleteAll();
        rebelRepository.save(luke);
        rebelRepository.save(leia);
        inventoryRepository.deleteAll();
        inventoryRepository.save(lukeInv);
        inventoryRepository.save(leiaInv);
    }

    @Test
    void should_add_to_list_when_no_same_name_item() throws TradeFailureException {
        Item addedItem = new Item("water", 1, 2);
        tradeUseCase.handle(lukeInv.getEntityUUID(), new Item("doritos", 2, 1),
                leiaInv.getEntityUUID(), addedItem);
        assertTrue(inventoryRepository.findById(lukeInv.getEntityUUID()).orElseThrow().getItemList().contains(addedItem));
    }

    @Test
    void should_set_quantity_when_same_name_item() throws TradeFailureException {
        Item addedItem = new Item("water", 1, 2);
        lukeInv.getItemList().add(new Item("water", 2, 1));

        tradeUseCase.handle(lukeInv.getEntityUUID(), new Item("doritos", 2, 1),
                leiaInv.getEntityUUID(), addedItem);
        assertEquals(3, (int) inventoryRepository.findItemByName(lukeInv.getEntityUUID(), "water").orElseThrow().getQuantity());
    }
}