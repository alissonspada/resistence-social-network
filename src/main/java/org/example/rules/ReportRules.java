package org.example.rules;

import org.example.repositories.RebelRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ReportRules {

    private final RebelRepository rebelRepository;

    public ReportRules(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    public List<UUID> handle(UUID sourceId, UUID reportedId) throws IllegalAccessException {
        UUID toBeReportedId = rebelRepository.findById(reportedId).orElseThrow().getId();

        if (rebelRepository.findById(sourceId).orElseThrow().getReportedRebels().contains(toBeReportedId))
            throw new IllegalAccessException("Rebel already reported");

        return new ArrayList<>(Arrays.asList(sourceId, reportedId));
    }
}
