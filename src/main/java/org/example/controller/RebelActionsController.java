package org.example.controller;

import org.example.repositories.InventoryRepository;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;
import org.example.request.RequestReport;
import org.example.request.RequestLocationUpdate;
import org.example.usecase.LocationUpdateUseCase;
import org.example.usecase.ReportUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RebelActionsController {
    private final RebelRepository rebelRepo;
    private final LocationRepository locationRepo;
    private final InventoryRepository inventoryRepo;

    @Autowired
    public RebelActionsController(RebelRepository rebelRepo, LocationRepository locationRepo, InventoryRepository inventoryRepo) {
        this.rebelRepo = rebelRepo;
        this.locationRepo = locationRepo;
        this.inventoryRepo = inventoryRepo;
    }

    @PatchMapping("/report")
    public ResponseEntity<String> handleReport(@RequestBody RequestReport requestReport) {
        String response = new ReportUseCase(rebelRepo).handle(requestReport.sourceId(), requestReport.targetId());
        return ResponseEntity.ok(response + ". reportedId= " + requestReport.targetId());
    }

    @PatchMapping("/locationUpdate")
    public ResponseEntity<String> handleTrade(@RequestBody RequestLocationUpdate requestLocationUpdate) {
        LocationUpdateUseCase locationUpdateUseCase = new LocationUpdateUseCase(locationRepo);
        String response = locationUpdateUseCase.handle(requestLocationUpdate.locationId(), requestLocationUpdate.newLocation());
        return ResponseEntity.ok(response);
    }
}
