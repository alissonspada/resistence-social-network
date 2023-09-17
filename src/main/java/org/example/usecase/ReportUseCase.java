package org.example.usecase;

import org.example.repositories.RebelRepository;
import org.example.rules.ReportRules;

import java.util.List;
import java.util.UUID;

public class ReportUseCase {
    private final RebelRepository rebelRepository;

    public ReportUseCase(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    public void handle(UUID sourceId, UUID reportedId) {
        List<UUID> ids = new ReportRules(rebelRepository).handle(sourceId, reportedId);

        if (!ids.isEmpty()) {
            rebelRepository.findById(ids.get(1)).get().setReportCounterUp();
            rebelRepository.findById(ids.get(0)).get().getReportedRebels().add(ids.get(1));
        }
    }
}
