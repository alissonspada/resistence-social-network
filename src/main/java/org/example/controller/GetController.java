package org.example.controller;

import org.example.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {
    private final RebelRepository rebelRepo;
    private final LocationRepository locationRepo;
    private final InventoryRepository inventoryRepo;

    public GetController(RebelRepository rebelRepo, LocationRepository locationRepo, InventoryRepository inventoryRepo) {
        this.rebelRepo = rebelRepo;
        this.locationRepo = locationRepo;
        this.inventoryRepo = inventoryRepo;
    }
    @GetMapping("/")
    public ResponseEntity<String> displayMainPage() {
        return ResponseEntity.ok("Welcome to the Star Wars Resistance Social Network!");
    }

    @GetMapping("/allrebels")
    public ResponseEntity<String> getAllRebels() {
        return ResponseEntity.ok(rebelRepo.findAll() + "\n" +  locationRepo.findAll() + "\n" + inventoryRepo.findAll());
    }
}
