package org.example.usecase;

import org.example.model.Location;
import org.example.repositories.LocationRepository;
import org.example.rules.LocationUpdateRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationUpdateUseCase {
    private final LocationRepository locationRepository;
    private final LocationUpdateRules locationUpdateRules;

    @Autowired
    public LocationUpdateUseCase(LocationRepository locationRepository, LocationUpdateRules locationUpdateRules) {
        this.locationRepository = locationRepository;
        this.locationUpdateRules = locationUpdateRules;
    }

    public void handle(Integer locationId, Location newLocation) {
        Location formattedNewLocation = locationUpdateRules.handle(locationId, newLocation).get();

        if (formattedNewLocation.getLatitude() != null) {
            locationRepository.save(formattedNewLocation);
        }
    }
}
