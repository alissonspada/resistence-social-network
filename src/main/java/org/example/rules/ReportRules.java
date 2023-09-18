package org.example.rules;

import org.example.repositories.RebelRepository;

import java.util.*;

public class ReportRules {
    private final RebelRepository rebelRepo;

    public ReportRules(RebelRepository rebelRepo) {
        this.rebelRepo = rebelRepo;
    }

    public List<UUID> handle(UUID sourceId, UUID targetId) throws NoSuchElementException {
        if (rebelRepo.existsById(sourceId)) throw new NoSuchElementException("source not found");
        if (rebelRepo.existsById(targetId)) throw new NoSuchElementException("target not found");

        if (rebelRepo.findById(sourceId).orElseThrow().getReportedRebels().contains(targetId)) return new ArrayList<>();

        return new ArrayList<>(Arrays.asList(sourceId, targetId));
    }
}
