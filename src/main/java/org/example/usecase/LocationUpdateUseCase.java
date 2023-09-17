package org.example.usecase;

import org.example.model.Location;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;
import org.example.rules.LocationUpdateRules;

import java.util.UUID;

public class LocationUpdateUseCase {
    private final RebelRepository rebelRepository;
    private final LocationRepository locationRepository;

    public LocationUpdateUseCase(RebelRepository rebelRepository, LocationRepository locationRepository) {
        this.rebelRepository = rebelRepository;
        this.locationRepository = locationRepository;
    }

    public void handle(UUID rebelId, UUID locationId, Location location) {
        Location newLocation = new LocationUpdateRules(rebelRepository).handle(rebelId, location);
        location.setId(locationId);
        locationRepository.findById(locationId).orElseThrow().setNewLocation(newLocation);
    }
}
