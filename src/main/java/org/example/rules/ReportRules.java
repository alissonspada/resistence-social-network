package org.example.rules;

import org.example.repositories.RebelRepository;

import java.util.UUID;

public class ReportRules {
    private final RebelRepository rebelRepo;

    public ReportRules(RebelRepository rebelRepo) {
        this.rebelRepo = rebelRepo;
    }

    public String handle(UUID sourceId, UUID targetId) {
        if (!rebelRepo.existsById(sourceId)) return "source not found";
        if (!rebelRepo.existsById(targetId)) return "target not found";
        if (rebelRepo.findById(sourceId).get().getReportedRebels().contains(targetId)) return "rebel already reported";

        return "";
    }
}
