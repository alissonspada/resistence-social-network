package org.example.usecase;

import org.example.model.Location;
import org.example.model.Rebel;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LocationUpdateUseCaseTest {

    @Autowired
    LocationRepository locationRepository;
    RebelRepository rebelRepository = new RebelRepository();
    LocationUpdateUseCase locationUpdateUseCase = new LocationUpdateUseCase(locationRepository);

    @Test
    void should_save_new_location(){
        Location location = new Location(150.1,12.2,"xereca");
        Rebel rebel = new Rebel("jacinto pinto no rego", 1,"masculino");
        locationRepository.save(location);
        rebelRepository.save(rebel);
        Location newLocation = new Location(90.0,30.2,"manjuba");
        locationUpdateUseCase.handle(location.getEntityId(), newLocation);
        Assertions.assertEquals(newLocation.toString(), locationRepository.findById(location.getEntityId()).orElseThrow().toString());
    }
}
