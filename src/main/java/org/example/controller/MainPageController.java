package org.example.controller;

import org.example.repositories.RebelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageController {
    private final RebelRepository rebelRepo;
    public MainPageController(RebelRepository rebelRepo) {
        this.rebelRepo = rebelRepo;
    }
    @GetMapping("/")
    public ResponseEntity<String> displayMainPage() {
        return ResponseEntity.ok("Welcome to the Star Wars Resistance Social Network!");
    }

    @GetMapping("/allrebels")
    public ResponseEntity<String> getAllRebels() {

        return ResponseEntity.ok(rebelRepo.findAll().toString());
    }
}
