package org.example.rules;

import org.example.model.Inventory;
import org.example.model.Item;
import org.example.model.Rebel;
import org.example.repositories.InventoryRepository;
import org.example.repositories.RebelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
class TradeRulesTest {
    private final RebelRepository rebelRepository = new RebelRepository();
    private final InventoryRepository inventoryRepository = new InventoryRepository();
    Rebel luke = new Rebel("luke", 18, "male");
    Rebel leia = new Rebel("leia", 30, "female");
    Inventory lukeInv = new Inventory(luke.getId(), new ArrayList<>(List.of(new Item("doritos", 2, 1))));
    Inventory leiaInv = new Inventory(leia.getId(), new ArrayList<>(List.of(new Item("water", 1, 2))));
    TradeRules tradeRules = new TradeRules(inventoryRepository, rebelRepository);

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
    void should_throw_NoSuchElementException_when_no_such_item_source() {

        Exception e = assertThrows(NoSuchElementException.class, () ->
                tradeRules.check(lukeInv.getId(), new Item("food", 2, 1),
                        leiaInv.getId(), new Item("water", 1, 2))
        );
        assertTrue(e.getMessage().contains("no such item source"));
    }

    @Test
    void should_throw_NoSuchElementException_when_no_such_item_target() {
        Exception e = assertThrows(NoSuchElementException.class, () ->
                tradeRules.check(lukeInv.getId(), new Item("doritos", 2, 1),
                        leiaInv.getId(), new Item("cheetos", 1, 2))
        );
        assertTrue(e.getMessage().contains("no such item target"));
    }

    @Test
    void should_throw_TradeFailureException_when_source_traitor() {
        luke.setReportCounterUp();
        luke.setReportCounterUp();
        luke.setReportCounterUp();
        Exception e = assertThrows(TradeFailureException.class, () ->
                tradeRules.check(lukeInv.getId(), new Item("doritos", 2, 1),
                        leiaInv.getId(), new Item("cheetos", 1, 2))
        );
        assertTrue(e.getMessage().contains("source is either a traitor or could not be found"));
    }

    @Test
    void should_throw_TradeFailureException_when_points_do_not_match() {
        Exception e = assertThrows(TradeFailureException.class, () ->
                tradeRules.check(lukeInv.getId(), new Item("doritos", 2, 2),
                        leiaInv.getId(), new Item("water", 1, 2))
        );
        assertTrue(e.getMessage().contains("points do not match"));
    }

    @Test
    void should_throw_TradeFailureException_when_insufficient_funds_source() {
        Exception e = assertThrows(TradeFailureException.class, () ->
                tradeRules.check(lukeInv.getId(), new Item("doritos", 4, 1),
                        leiaInv.getId(), new Item("water", 2, 2))
        );
        assertTrue(e.getMessage().contains("insufficient funds source"));
    }
}