package org.example.rules;

import org.example.model.Location;
import org.example.repositories.RebelRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

public class LocationUpdateRules {
        private final RebelRepository rebelRepository;

    public LocationUpdateRules(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    public Location handle(UUID id, Location location) {
        rebelRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("rebel not found")
        );
        GenericRules genericRules = new GenericRules();
        return new Location(
                genericRules.handle(location.getLatitude(), 180),
                genericRules.handle(location.getLongitude(), 90),
                genericRules.handle(location.getBase())
        );
    }
}
