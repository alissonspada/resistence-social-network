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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationUseCaseTest {
    private final RebelRepository rebelRepository = new RebelRepository();
    private final InventoryRepository inventoryRepository = new InventoryRepository();
    private final LocationRepository locationRepository = new LocationRepository();
    private final Rebel luke = new Rebel("luke", 18, "male");
    private final Rebel leia = new Rebel("leia", 30, "female");
    private final Location lukeLocation = new Location(0.2, 21.3, "base/galaxy");
    private final Location leiaLocation = new Location(0.2, 21.3, "base/galaxy");
    private final Inventory lukeInv = new Inventory(luke.getId(),
            new ArrayList<>(List.of(
                    new Item("doritos", 2, 1))
            )
    );
    private final Inventory leiaInv = new Inventory(leia.getId(),
            new ArrayList<>(List.of(
                    new Item("water", 1, 2))
            )
    );

    @BeforeEach
    void setUp() {
        rebelRepository.deleteAll();
        rebelRepository.save(luke);
        rebelRepository.save(leia);
        inventoryRepository.deleteAll();
        inventoryRepository.save(lukeInv);
        inventoryRepository.save(leiaInv);
        locationRepository.deleteAll();
        locationRepository.save(lukeLocation);
        locationRepository.save(leiaLocation);
    }

    @Test
    void should_save_rebel() {
        assertDoesNotThrow(() -> rebelRepository.findById(luke.getId()));
    }

    @Test
    void should_save_location() {
        assertDoesNotThrow(() -> locationRepository.findById(lukeLocation.getId()));
    }

    @Test
    void should_save_inventory() {
        assertDoesNotThrow(() -> inventoryRepository.findById(lukeInv.getId()));
    }
}