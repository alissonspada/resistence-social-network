package org.example;

import org.example.model.Location;
import org.example.model.Rebel;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;
import org.example.usecase.LocationUpdateUseCase;

public class Main {
    public static void main(String[] args) throws Exception{
        RebelRepository rebelRepository = new RebelRepository();

        Rebel rebel = new Rebel("luke", 18, "male");
        Rebel rebel2 = new Rebel("leia", 20, "female");
        rebelRepository.save(rebel2);
        rebelRepository.save(rebel);

        LocationRepository locationRepository = new LocationRepository();
        Location location1 = new Location(21.1, 22.4, "base");
        locationRepository.save(location1);


        LocationUpdateUseCase locationUseCase = new LocationUpdateUseCase(rebelRepository, locationRepository);
        locationUseCase.handle(rebel.getId(), location1.getId(), new Location(
                -500.7, null, "base"));

        System.out.println(locationRepository.findAll());
    }
}