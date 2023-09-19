package org.example.rules;

import org.example.model.Location;
import org.example.repositories.LocationRepository;

public class LocationUpdateRules {
    private final LocationRepository locationRepo;

    public LocationUpdateRules(LocationRepository locationRepo) {
        this.locationRepo = locationRepo;
    }

    public Location handle(Integer locationId, Location newLocation) {
        Location oldLocation = locationRepo.findById(locationId).orElse(null);

        if (oldLocation == null) return new Location();

        GenericRules genericRules = new GenericRules();

        oldLocation.setNewLocation(
                genericRules.handle(newLocation.getLatitude(), 90),
                genericRules.handle(newLocation.getLongitude(), 180),
                genericRules.handle(newLocation.getBase())
        );

        return oldLocation;
    }
}
