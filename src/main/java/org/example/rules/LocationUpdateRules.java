package org.example.rules;

import org.example.model.Location;
import org.example.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationUpdateRules {
    private final LocationRepository locationRepo;
    private final GenericRules genericRules = new GenericRules();

    @Autowired
    public LocationUpdateRules(LocationRepository locationRepo) {
        this.locationRepo = locationRepo;
    }

    public Optional<Location> handle(Integer locationId, Location newLocation) {
        Optional<Location> oldLocation = locationRepo.findById(locationId);

        if (oldLocation.isEmpty()) return Optional.empty();

        oldLocation.get().setNewLocation(
                genericRules.handle(newLocation.getLatitude(), 90),
                genericRules.handle(newLocation.getLongitude(), 180),
                genericRules.handle(newLocation.getBase())
        );

        return oldLocation;
    }
}
