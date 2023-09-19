package org.example.usecase;

import org.example.model.Location;
import org.example.repositories.LocationRepository;
import org.example.rules.LocationUpdateRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationUpdateUseCase {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationUpdateUseCase(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public String handle(Integer locationId, Location newLocation) {
        Location formattedNewLocation = new LocationUpdateRules(locationRepository).handle(locationId, newLocation);

        if (formattedNewLocation.getLatitude() != null) {
            return "Location updated to: \n" + formattedNewLocation;
        }
        return "Location update failed because location could not be found";
    }
}
