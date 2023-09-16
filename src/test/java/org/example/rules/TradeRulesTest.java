package org.example.rules;

import org.example.model.Inventory;
import org.example.model.Item;
import org.example.model.Rebel;
import org.example.repositories.InventoryRepository;
import org.example.repositories.RebelRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
class TradeRulesTest {
    private final RebelRepository rebelRepository = new RebelRepository();
    private final InventoryRepository inventoryRepository = new InventoryRepository();

    @Test
    void should_throw_NoSuchElementException_when_source_does_not_have_traded_item() {
        Rebel luke = new Rebel("luke", 18, "male");
        Rebel leia = new Rebel("leia", 30, "female");
        rebelRepository.save(luke);
        rebelRepository.save(leia);
        Inventory lukeInv = new Inventory(luke.getId(), new ArrayList<>(List.of(new Item("doritos", 2, 1))));
        Inventory leiaInv = new Inventory(leia.getId(), new ArrayList<>(List.of(new Item("water", 1, 2))));
        inventoryRepository.save(lukeInv);
        inventoryRepository.save(leiaInv);
        TradeRules tradeRules = new TradeRules(inventoryRepository, rebelRepository);
        Exception e = assertThrows(NoSuchElementException.class, () ->
                tradeRules.check(lukeInv.getId(), new Item("food", 2, 1),
                        leiaInv.getId(), new Item("water", 1, 2))
        );
        assertTrue(e.getMessage().contains("no such item source"));
    }

    @Test
    void should_throw_NoSuchElementException_when_target_does_not_have_traded_item() {
        Rebel luke = new Rebel("luke", 18, "male");
        Rebel leia = new Rebel("leia", 30, "female");
        Inventory lukeInv = new Inventory(luke.getId(), new ArrayList<>(List.of(new Item("food", 2, 1))));
        Inventory leiaInv = new Inventory(leia.getId(), new ArrayList<>(List.of(new Item("doritos", 1, 2))));
        rebelRepository.save(luke);
        rebelRepository.save(leia);
        inventoryRepository.save(lukeInv);
        inventoryRepository.save(leiaInv);
        System.out.println(inventoryRepository.findById(lukeInv.getId()));
        TradeRules tradeRules = new TradeRules(inventoryRepository, rebelRepository);
        Exception e = assertThrows(NoSuchElementException.class, () ->
                tradeRules.check(lukeInv.getId(), new Item("food", 2, 1),
                        leiaInv.getId(), new Item("water", 1, 2))
        );
        System.out.println(e.getMessage());
        assertTrue(e.getMessage().contains("no such item target"));
    }
}