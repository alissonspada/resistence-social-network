package org.example.usecase;

import org.example.model.Rebel;
import org.example.repositories.RebelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlliesTraitorsPercentagesUseCaseTest {

    @Autowired
    private RebelRepository rebelRepo;

    @Test
    void should_return_percentages_string() {
        rebelRepo.save(new Rebel("luke", 28, "male"));

        AlliesTraitorsPercentagesUseCase alliesTraitorsPercentagesUseCase = new AlliesTraitorsPercentagesUseCase(rebelRepo);
        List<String> actualPercentages = alliesTraitorsPercentagesUseCase.handle();

        NumberFormat percentagesFormat = NumberFormat.getPercentInstance();
        String alliesPercentage = percentagesFormat.format(1);
        String traitorsPercentage = percentagesFormat.format(0);
        List<String> expectedPercentages = new ArrayList<>(Arrays.asList(alliesPercentage, traitorsPercentage));
        assertEquals(expectedPercentages, actualPercentages);
    }

}