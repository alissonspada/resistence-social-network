package org.example.usecase;

import org.example.model.Inventory;
import org.example.model.Location;
import org.example.model.Rebel;
import org.example.repositories.InventoryRepository;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;

public class RegistrationUseCase {

    private final RebelRepository rebelRepository = new RebelRepository();
    private final LocationRepository locationRepository = new LocationRepository();
    private final InventoryRepository inventoryRepository = new InventoryRepository();

    public void handle(Rebel rebelde, Location location, Inventory inventory){

        rebelRepository.save(rebelde);

        locationRepository.save(location);

        inventoryRepository.save(inventory);

    }

}
