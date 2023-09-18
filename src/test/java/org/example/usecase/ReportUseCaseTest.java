package org.example.usecase;

import org.example.model.Rebel;
import org.example.repositories.RebelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReportUseCaseTest {
    private final RebelRepository rebelRepository = new RebelRepository();
    private final ReportUseCase reportUseCase = new ReportUseCase(rebelRepository);

    @Test
    void should_report_a_rebel() {

        Rebel addRebel1 = new Rebel("zezinho",1,"masculino");
        Rebel addRebel2 = new Rebel("pedrinho",1,"feminino");
        rebelRepository.save(addRebel1);
        rebelRepository.save(addRebel2);

        reportUseCase.handle(addRebel1.getEntityUUID(), addRebel2.getEntityUUID());
        Assertions.assertEquals(1, (int) rebelRepository.findById(addRebel2.getEntityUUID()).orElseThrow().getReportCounter());
    }

}
