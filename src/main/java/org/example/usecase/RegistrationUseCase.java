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

    private final RebelRepository rebelRepository;
    private final LocationRepository locationRepository;
    private final InventoryRepository inventoryRepository;

    public RegistrationUseCase(RebelRepository rebelRepository, LocationRepository locationRepository, InventoryRepository inventoryRepository) {
        this.rebelRepository = rebelRepository;
        this.locationRepository = locationRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public void handle(Rebel rebel, Location location, Inventory inventory) {
        RegistrationRules registrationRules = new RegistrationRules();
        List<GenericEntity> data = registrationRules.format(rebel, location, inventory);

        rebelRepository.save( (Rebel) data.get(0) );
        locationRepository.save( (Location) data.get(1) );
        inventoryRepository.save( (Inventory) data.get(2) );
    }
}
