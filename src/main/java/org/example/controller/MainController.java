package org.example.controller;

import org.example.repositories.InventoryRepository;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;
import org.example.request.SignUpRequest;
import org.example.usecase.RegistrationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private final RebelRepository rebelRepo;
    private final LocationRepository locationRepo;
    private final InventoryRepository inventoryRepo;

    @Autowired
    public MainController(RebelRepository rebelRepo, LocationRepository locationRepo, InventoryRepository inventoryRepo) {
        this.rebelRepo = rebelRepo;
        this.locationRepo = locationRepo;
        this.inventoryRepo = inventoryRepo;
    }

    @GetMapping("/")
    public ResponseEntity<String> mainPage() {
        return ResponseEntity.ok("Welcome!");
    }
    @PostMapping("/signup")
    public ResponseEntity<String> registerRebel(@RequestBody SignUpRequest signUpData) {
        RegistrationUseCase registrationUseCase = new RegistrationUseCase(rebelRepo, locationRepo, inventoryRepo);
        registrationUseCase.handle(signUpData.rebel(), signUpData.location(), signUpData.inventory());
        return ResponseEntity.ok(String.join("\n", rebelRepo.findAll().toString(),
                locationRepo.findAll().toString(),
                inventoryRepo.findAll().toString()));
    }
}
