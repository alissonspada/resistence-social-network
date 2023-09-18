package org.example.repositories;

import org.example.model.Item;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryRepositoryTest {

    @Test
    void findItemByName_should_return_Optional_Empty_when_item_not_found() {
        InventoryRepository inventoryRepo = new InventoryRepository();
        Optional<Item> actualItem = inventoryRepo.findItemByName(UUID.randomUUID(), "alberto");
        Optional<Item> expectedItem = Optional.empty();
        assertEquals(expectedItem, actualItem);
    }
}