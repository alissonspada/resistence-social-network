package org.example.usecase;

import org.example.repositories.RebelRepository;
import org.example.rules.ReportRules;

import java.util.UUID;

public class ReportUseCase {
    private final RebelRepository rebelRepository;

    public ReportUseCase(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    public String handle(UUID sourceId, UUID targetId) {
        String response = new ReportRules(rebelRepository).handle(sourceId, targetId);

        if (response.isEmpty()) {
            rebelRepository.findById(targetId).get().setReportCounterUp();
            rebelRepository.findById(sourceId).get().getReportedRebels().add(targetId);
            return "Report successful";
        }
        return response;
    }
}
