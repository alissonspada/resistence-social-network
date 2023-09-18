package org.example.controller;

import org.example.repositories.InventoryRepository;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;
import org.example.request.RequestLocationUpdate;
import org.example.request.RequestReport;
import org.example.request.RequestTrade;
import org.example.rules.TradeFailureException;
import org.example.usecase.LocationUpdateUseCase;
import org.example.usecase.ReportUseCase;
import org.example.usecase.TradeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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
        UUID sourceRebelId;
        UUID targetRebelId;
        try {
            sourceRebelId = rebelRepo
                    .findAll()
                    .stream()
                    .filter(rebel -> rebel.getName().equals(requestReport.sourceName()))
                    .findFirst()
                    .orElseThrow()
                    .getUuid();
            targetRebelId = rebelRepo
                    .findAll()
                    .stream()
                    .filter(rebel -> rebel.getName().equals(requestReport.targetName()))
                    .findFirst()
                    .orElseThrow()
                    .getUuid();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("no such rebel with name " + requestReport.sourceName());
        }
        String response = new ReportUseCase(rebelRepo).handle(sourceRebelId, targetRebelId);
        return ResponseEntity.ok(response + ". reportedId= " + targetRebelId);
    }

    @PatchMapping("/locationUpdate")
    public ResponseEntity<String> handleLocationUpdate(@RequestBody RequestLocationUpdate requestLocationUpdate) {
        LocationUpdateUseCase locationUpdateUseCase = new LocationUpdateUseCase(locationRepo);
        String response = locationUpdateUseCase.handle(requestLocationUpdate.locationId(), requestLocationUpdate.newLocation());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/trade")
    public ResponseEntity<String> handleTrade(@RequestBody RequestTrade requestTrade) {
        TradeUseCase tradeUseCase = new TradeUseCase(inventoryRepo, rebelRepo);
        try {
            tradeUseCase.handle(requestTrade.sourceInventoryId(), requestTrade.sourceTradeItem(),
                    requestTrade.targetInventoryId(), requestTrade.targetTradeItem());
        } catch (TradeFailureException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Trade successful");
    }
}
