package org.example.rules;

import org.example.model.Location;
import org.example.model.Rebel;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

public class LocationRules {
        private final RebelRepository rebelRepository;

    public LocationRules(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    public Location handle(UUID id, Location location) {
        rebelRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("no such rebel")
        );
        GenericRules genericRules = new GenericRules();
        return new Location(
                genericRules.handle(location.getLatitude(), 180),
                genericRules.handle(location.getLongitude(), 90),
                genericRules.handle(location.getBase()));
    }
}
