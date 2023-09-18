package org.example.controller;

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
    private ReportUseCase reportUseCase;
    private LocationUpdateUseCase locationUpdateUseCase;
    private TradeUseCase tradeUseCase;
    @Autowired
    public RebelActionsController(ReportUseCase reportUseCase, LocationUpdateUseCase locationUpdateUseCase, TradeUseCase tradeUseCase) {
        this.reportUseCase = reportUseCase;
        this.locationUpdateUseCase = locationUpdateUseCase;
        this.tradeUseCase = tradeUseCase;
    }

    @PatchMapping("/report")
    public ResponseEntity<String> handleReport(@RequestBody RequestReport requestReport) {
        UUID sourceRebelId;
        UUID targetRebelId;
        try {
            sourceRebelId = rebelRepo.findByName(requestReport.sourceName()).get().getEntityUUID();
            targetRebelId = rebelRepo.findByName(requestReport.targetName()).get().getEntityUUID();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("no such rebels");
        }
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
