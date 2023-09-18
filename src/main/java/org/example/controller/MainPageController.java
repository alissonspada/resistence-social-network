package org.example.controller;

import org.example.repositories.InventoryRepository;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageController {
    private final RebelRepository rebelRepo;
    private final LocationRepository locationRepo;
    private final InventoryRepository inventoryRepo;

    public MainPageController(RebelRepository rebelRepo, LocationRepository locationRepo, InventoryRepository inventoryRepo) {
        this.rebelRepo = rebelRepo;
        this.locationRepo = locationRepo;
        this.inventoryRepo = inventoryRepo;
    }
    @GetMapping("/")
    public ResponseEntity<String> displayMainPage() {
        return ResponseEntity.ok("Welcome to the Star Wars Resistance Social Network!");
    }

    @GetMapping("/rebels")
    public ResponseEntity<String> getAllRebels() {
        return ResponseEntity.ok(rebelRepo.findAll() + "\n" +  locationRepo.findAll() + "\n" + inventoryRepo.findAll());
    }
}
