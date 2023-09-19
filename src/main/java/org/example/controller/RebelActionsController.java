package org.example.controller;

import org.example.repositories.InventoryRepository;
import org.example.repositories.LocationRepository;
import org.example.repositories.RebelRepository;
import org.example.request.RequestLocationUpdate;
import org.example.request.RequestReport;
import org.example.request.RequestTrade;
import org.example.rules.TradeFailureException;
import org.example.usecase.ReportUseCase;
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
        Integer sourceRebelId;
        Integer targetRebelId;
        try {
            sourceRebelId = rebelRepo.findByName(requestReport.sourceName()).get().getEntityId();
            targetRebelId = rebelRepo.findByName(requestReport.targetName()).get().getEntityId();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("no such rebels");
        }
        ReportUseCase reportUseCase = new ReportUseCase(rebelRepo);
        String response = reportUseCase.handle(sourceRebelId, targetRebelId);
        return ResponseEntity.ok(response + ". reportedId= " + targetRebelId);
    }

    @PatchMapping("/locationUpdate")
    public ResponseEntity<String> handleLocationUpdate(@RequestBody RequestLocationUpdate requestLocationUpdate) {
        UUID locationId;
        try {
            locationId = locationRepo
                    .findById(rebelRepo.findByName(requestLocationUpdate.rebelName()).orElseThrow().getEntityUUID())
                    .orElseThrow()
                    .getOwnerId();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("no such rebel");
        }
        String response = locationUpdateUseCase.handle(locationId, requestLocationUpdate.newLocation());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/trade")
    public ResponseEntity<String> handleTrade(@RequestBody RequestTrade requestTrade) {
        try {
            tradeUseCase.handle(requestTrade.sourceInventoryId(), requestTrade.sourceTradeItem(),
                    requestTrade.targetInventoryId(), requestTrade.targetTradeItem());
        } catch (TradeFailureException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Trade successful");
    }
}
