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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RegistrationUseCaseTest {
    private final RebelRepository rebelRepo = new RebelRepository();
    private final InventoryRepository inventoryRepo = new InventoryRepository();
    private final LocationRepository locationRepo = new LocationRepository();
    private final Rebel luke = new Rebel("luke", 18, "male");
    private final Rebel leia = new Rebel("leia", 30, "female");
    private final Location lukeLocation = new Location(0.2, 21.3, "base/galaxy");
    private final Location leiaLocation = new Location(0.2, 21.3, "base/galaxy");
    private final Inventory lukeInv = new Inventory(luke.getId(),
            new ArrayList<>( List.of( new Item("doritos", 2, 1)) )
    );
    private final Inventory leiaInv = new Inventory(leia.getId(),
            new ArrayList<>( List.of( new Item("water", 1, 2)) )
    );
    private final RegistrationUseCase registrationUseCase = new RegistrationUseCase(rebelRepo, locationRepo, inventoryRepo);

    @BeforeEach
    void setUp() {
        rebelRepo.deleteAll();
        inventoryRepo.deleteAll();
        locationRepo.deleteAll();

        registrationUseCase.handle(luke, lukeLocation, lukeInv);
        registrationUseCase.handle(leia, leiaLocation, leiaInv);
    }

    @Test
    void should_save_rebel() {
        assertDoesNotThrow(() -> rebelRepo.findById(luke.getId()));
    }

    @Test
    void should_save_location() {
        assertDoesNotThrow(() -> locationRepo.findById(lukeLocation.getId()));
    }

    @Test
    void should_save_inventory() {
        assertDoesNotThrow(() -> inventoryRepo.findById(lukeInv.getId()));
    }
}