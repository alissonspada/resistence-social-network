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

        Rebel rebel1 = new Rebel("zezinho",1,"masculino");
        Rebel rebel2 = new Rebel("pedrinho",1,"feminino");
        rebelRepository.save(rebel1);
        rebelRepository.save(rebel2);
        System.out.println(rebel1);

        reportUseCase.handle(rebel1.getId(), rebel2.getId());
        Assertions.assertEquals(1, rebelRepository.findById(rebel2.getId()).orElseThrow().getReportCounter());
    }

}
