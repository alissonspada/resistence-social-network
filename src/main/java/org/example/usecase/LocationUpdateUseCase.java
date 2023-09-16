package org.example.usecase;

import org.example.model.Location;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;
import org.example.rules.LocationRules;

import java.util.UUID;

public class LocationUpdateUseCase {
    private final RebelRepository rebelRepository;
    private final LocationRepository locationRepository;

    public LocationUpdateUseCase(RebelRepository rebelRepository, LocationRepository locationRepository) {
        this.rebelRepository = rebelRepository;
        this.locationRepository = locationRepository;
    }

    public void handle(UUID rebelId, UUID locationId, Location location) {
        LocationRules locationRules = new LocationRules(rebelRepository);
        Location newLocation = locationRules.handle(rebelId, location);
        locationRepository.findById(locationId).orElseThrow().setNewLocation(newLocation);
    }
}
