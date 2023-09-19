package org.example.rules;

import org.example.model.Location;
import org.example.model.Rebel;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LocationUpdateRulesTest {

    private final RebelRepository rebelRepo = new RebelRepository();
    private final LocationRepository locationRepo = new LocationRepository();

    @Test
    void should_throw_NoSuchElementException_when_rebel_not_found() {
        LocationUpdateRules locationUpdateRules = new LocationUpdateRules(locationRepo);
        Exception e = assertThrows(NoSuchElementException.class, () ->
                locationUpdateRules.handle(0, new Location())
        );
        assertTrue(e.getMessage().contains("rebel not found"));
    }

    @Test
    void should_return_new_location() {
        Rebel rebel = new Rebel("luke", 18, "male");
        rebelRepo.save(rebel);
        locationRepo.save(new Location(53.53, 41.665, "joao"));
        LocationUpdateRules locationUpdateRules = new LocationUpdateRules(locationRepo);
        Location expectedLocation = new Location(42.1, 22.5, "base");
        Location returnedLocation = locationUpdateRules.handle(rebel.getEntityId(), expectedLocation);
        assertEquals(expectedLocation.toString(), returnedLocation.toString());
    }
}