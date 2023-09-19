package org.example.usecase;

import org.example.model.Rebel;
import org.example.repositories.RebelRepository;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayPercentageUseCaseTest {

    private final RebelRepository rebelRepo = new RebelRepository();

    @Test
    void should_return_percentages_string() {
        rebelRepo.save(new Rebel("luke", 28, "male"));
        AlliesTraitorsPercentagesUseCase displayPercentageUseCase = new AlliesTraitorsPercentagesUseCase(rebelRepo);
        List<String> actualPercentages = displayPercentageUseCase.handle();
        NumberFormat percentagesFormat = NumberFormat.getPercentInstance();
        String alliesPercentage = percentagesFormat.format(1);
        String traitorsPercentage = percentagesFormat.format(0);
        List<String> expectedPercentages = new ArrayList<>(Arrays.asList(alliesPercentage, traitorsPercentage));
        assertEquals(expectedPercentages, actualPercentages);
    }

}