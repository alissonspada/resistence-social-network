package org.example.usecase;

import org.example.model.Rebel;
import org.example.repositories.RebelRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayPercentageUseCaseTest {

    private final RebelRepository rebelRepo = new RebelRepository();

    @Test
    void should_return_percentages_string() {
        rebelRepo.save(new Rebel("luke", 28, "male"));
        DisplayPercentageUseCase displayPercentageUseCase = new DisplayPercentageUseCase(rebelRepo);
        String actualPercentages = displayPercentageUseCase.handle();
        String expectedPercentages = "Allies: 100% Traitors: 0%";
        assertEquals(expectedPercentages, actualPercentages);
    }

}