package org.example.usecase;

import org.example.model.Rebel;
import org.example.repositories.RebelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReportUseCaseTest {
    @Autowired
    private RebelRepository rebelRepository;
    @Autowired
    private ReportUseCase reportUseCase;

    @Test
    void should_report_a_rebel() throws Exception {

        Rebel addRebel1 = new Rebel("zezinho",1,"masculino");
        Rebel addRebel2 = new Rebel("pedrinho",1,"feminino");
        rebelRepository.save(addRebel1);
        rebelRepository.save(addRebel2);

        reportUseCase.handle(addRebel1.getId(), addRebel2.getId());
        Assertions.assertEquals(1, (int) rebelRepository.findById(addRebel2.getId()).orElseThrow().getReportCounter());
    }

}
