package org.example.usecase;

import org.example.model.Inventory;
import org.example.model.Item;
import org.example.model.Location;
import org.example.model.Rebel;
import org.example.repositories.InventoryRepository;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RegistrationUseCaseTest {
    private final RebelRepository rebelRepo = new RebelRepository();
    private final InventoryRepository inventoryRepo = new InventoryRepository();
    private final LocationRepository locationRepo = new LocationRepository();
    private final Rebel luke = new Rebel("luke", 18, "male");
    private final Rebel leia = new Rebel("leia", 30, "female");
    private final Rebel hanSolo = new Rebel("han solo", 18, "male");
    private final Location lukeLocation = new Location(0.2, 21.3, "base/galaxy");
    private final Location leiaLocation = new Location(0.2, 21.3, "base/galaxy");
    private final Location hanSoloLocation = new Location(24.1, 42.1, "base");
    private final Inventory lukeInv = new Inventory(new ArrayList<>( List.of( new Item("doritos", 2, 1)) ));
    private final Inventory leiaInv = new Inventory(new ArrayList<>( List.of( new Item("water", 1, 2)) ));
    private final Inventory hanSoloInventory = new Inventory(new ArrayList<>(Arrays.asList(( new Item("doritos", 2, 1)),
            new Item("doritos", 20, 1))
            ));
    private final RegistrationUseCase registrationUseCase = new RegistrationUseCase(rebelRepo, locationRepo, inventoryRepo);

    @BeforeEach
    public void setUp() {
        registrationUseCase.handle(luke, lukeLocation, lukeInv);
        registrationUseCase.handle(leia, leiaLocation, leiaInv);
    }

    @Test
    void should_save_rebel() {
        assertNotEquals(Optional.empty(), rebelRepo.findById(luke.getEntityId()));
    }

    @Test
    void should_save_location() {
        assertNotEquals(Optional.empty(), locationRepo.findById(lukeLocation.getEntityId()));
    }

    @Test
    void should_save_inventory() {
        assertNotEquals(Optional.empty(), inventoryRepo.findById(lukeInv.getEntityId()));
    }

    @Test
    void new_inventory_should_contain_only_items_with_different_name() {
        List<Item> expectedInventoryList =
                new ArrayList<>(List.of(
                        new Item("doritos", 20, 1))
                );
        registrationUseCase.handle(hanSolo, hanSoloLocation, hanSoloInventory);
        assertEquals(expectedInventoryList.toString(), inventoryRepo.findById(hanSoloInventory.getEntityId()).orElseThrow().getItemList().toString());
    }
}