package org.example.usecase;

import org.example.model.Inventory;
import org.example.model.Item;
import org.example.model.Rebel;
import org.example.repositories.InventoryRepository;
import org.example.repositories.RebelRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemAveragesPerRebelUseCaseTest {

    private final InventoryRepository inventoryRepo = new InventoryRepository();
    private final Rebel luke = new Rebel("luke", 18, "male");
    private final Rebel leia = new Rebel("leia", 30, "female");
    private final Inventory lukeInv = new Inventory(new ArrayList<>( List.of( new Item("doritos", 2, 1)) ));
    private final Inventory leiaInv = new Inventory(new ArrayList<>( List.of( new Item("water", 1, 2)) ));
    private final RebelRepository rebelRepo = new RebelRepository();

    @Test
    void should_return_averages_string() {
        rebelRepo.save(luke);
        rebelRepo.save(leia);
        inventoryRepo.save(lukeInv);
        inventoryRepo.save(leiaInv);
        ItemAveragesPerRebelUseCase itemAveragesPerRebelUseCase = new ItemAveragesPerRebelUseCase(inventoryRepo);
        String expectedAverages = "doritos " + "1 " + "water " + "0 ";
        String actualAverages = itemAveragesPerRebelUseCase.handle();
        assertEquals(expectedAverages, actualAverages);
    }

}