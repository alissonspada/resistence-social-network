package org.example.usecase;

import org.example.model.Inventory;
import org.example.model.Item;
import org.example.model.Rebel;
import org.example.repositories.InventoryRepository;
import org.example.repositories.RebelRepository;
import org.example.rules.TradeFailureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TradeUseCaseTest {
    @Autowired
    private RebelRepository rebelRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    private final Rebel luke = new Rebel("luke", 18, "male");
    private final Rebel leia = new Rebel("leia", 30, "female");
    private final Inventory lukeInv = new Inventory(new ArrayList<>( List.of( new Item("doritos", 2, 1)) ));
    private final Inventory leiaInv = new Inventory(new ArrayList<>( List.of( new Item("water", 1, 2)) ));
    @Autowired
    private TradeUseCase tradeUseCase;

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
        tradeUseCase.handle(lukeInv.getEntityId(), new Item("doritos", 2, 1),
                leiaInv.getEntityId(), addedItem);
        assertTrue(inventoryRepository.findById(lukeInv.getEntityId()).orElseThrow().getItemList().contains(addedItem));
    }

    @Test
    void should_set_quantity_when_same_name_item() throws TradeFailureException {
        Item addedItem = new Item("water", 1, 2);
        lukeInv.getItemList().add(new Item("water", 2, 1));

        tradeUseCase.handle(lukeInv.getEntityId(), new Item("doritos", 2, 1),
                leiaInv.getEntityId(), addedItem);
        assertEquals(3, (int) inventoryRepository.findItemByName(lukeInv.getEntityId(), "water").orElseThrow().getQuantity());
    }
}