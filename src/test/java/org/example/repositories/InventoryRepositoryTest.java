package org.example.repositories;

import org.example.model.Item;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryRepositoryTest {
    @Test
    void findItemByName_should_return_Optional_Empty_when_item_not_found() {
        InventoryRepository inventoryRepo = new InventoryRepository();
        Optional<Item> actualItem = inventoryRepo.findItemByName(0, "alberto");
        Optional<Item> expectedItem = Optional.empty();
        assertEquals(expectedItem, actualItem);
    }
}