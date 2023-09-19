package org.example.usecase;

import org.example.model.Inventory;
import org.example.model.Item;
import org.example.model.Rebel;
import org.example.repositories.InventoryRepository;
import org.example.repositories.RebelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ItemAveragesPerRebelUseCaseTest {
    @Autowired
    private InventoryRepository inventoryRepo;
    @Autowired
    private RebelRepository rebelRepo;
    private final Rebel luke = new Rebel("luke", 18, "male");
    private final Rebel leia = new Rebel("leia", 30, "female");
    private final Inventory lukeInv = new Inventory(new ArrayList<>( List.of( new Item("doritos", 2, 1)) ));
    private final Inventory leiaInv = new Inventory(new ArrayList<>( List.of( new Item("water", 1, 2)) ));

    @Test
    void should_return_averages_string() {
        rebelRepo.save(luke);
        rebelRepo.save(leia);
        inventoryRepo.save(lukeInv);
        inventoryRepo.save(leiaInv);

        ItemAveragesPerRebelUseCase itemAveragesPerRebelUseCase = new ItemAveragesPerRebelUseCase(inventoryRepo);
        Map<String, Integer> expectedAverages = new HashMap<>();
        expectedAverages.put("doritos", 1);
        expectedAverages.put("water", 0);
        Map<String, Integer> actualAverages = itemAveragesPerRebelUseCase.handle();
        assertEquals(expectedAverages.toString(), actualAverages.toString());
    }

}