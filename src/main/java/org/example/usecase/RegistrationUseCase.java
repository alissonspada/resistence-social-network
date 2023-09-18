package org.example.usecase;

import org.example.model.Inventory;
import org.example.model.Location;
import org.example.model.Rebel;
import org.example.repositories.GenericEntity;
import org.example.repositories.InventoryRepository;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;
import org.example.rules.RegistrationRules;

import java.util.List;

public class RegistrationUseCase {
    private final RebelRepository rebelRepo;
    private final LocationRepository locationRepo;
    private final InventoryRepository inventoryRepo;

    public RegistrationUseCase(RebelRepository rebelRepo, LocationRepository locationRepo, InventoryRepository inventoryRepo) {
        this.rebelRepo = rebelRepo;
        this.locationRepo = locationRepo;
        this.inventoryRepo = inventoryRepo;
    }

    public void handle(Rebel rebel, Location location, Inventory inventory) {
        RegistrationRules registrationRules = new RegistrationRules();
        List<GenericEntity> data = registrationRules.format(rebel, location, inventory);

        Inventory fInventory = (Inventory) data.get(2);
        fInventory.setOwnerId(rebel.getUuid());
        Location fLocation = (Location) data.get(1);
        fLocation.setOwnerId(rebel.getUuid());

        rebelRepo.save((Rebel) data.get(0));
        locationRepo.save((Location) data.get(1));
        inventoryRepo.save((Inventory) data.get(2));
    }
}
