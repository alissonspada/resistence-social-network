package org.example.controller;

import org.example.repositories.InventoryRepository;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;
import org.example.request.RequestSignUp;
import org.example.usecase.RegistrationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    private final RebelRepository rebelRepo;
    private final LocationRepository locationRepo;
    private final InventoryRepository inventoryRepo;

    @Autowired
    public SignUpController(RebelRepository rebelRepo, LocationRepository locationRepo, InventoryRepository inventoryRepo) {
        this.rebelRepo = rebelRepo;
        this.locationRepo = locationRepo;
        this.inventoryRepo = inventoryRepo;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> handleSignUp(@RequestBody RequestSignUp signUpData) {
        RegistrationUseCase registrationUseCase = new RegistrationUseCase(rebelRepo, locationRepo, inventoryRepo);
        registrationUseCase.handle(signUpData.rebel(), signUpData.location(), signUpData.inventory());
        return ResponseEntity.ok("Registration successful\n\n" + signUpData);
    }
}
