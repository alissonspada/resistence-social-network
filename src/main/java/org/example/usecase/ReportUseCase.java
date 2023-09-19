package org.example.usecase;

import org.example.repositories.RebelRepository;
import org.example.rules.ReportRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportUseCase {
    private final RebelRepository rebelRepository;

    @Autowired
    public ReportUseCase(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    public void handle(Integer sourceId, Integer targetId) throws Exception {
        new ReportRules(rebelRepository).handle(sourceId, targetId);

        rebelRepository.findById(targetId).get().setReportCounterUp();
        rebelRepository.findById(sourceId).get().getReportedRebels().add(targetId);
    }
}
