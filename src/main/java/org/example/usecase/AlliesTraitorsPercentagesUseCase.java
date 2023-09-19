package org.example.usecase;

import org.example.model.Rebel;
import org.example.repositories.RebelRepository;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlliesTraitorsPercentagesUseCase {
    private final RebelRepository rebelRepository;

    public AlliesTraitorsPercentagesUseCase(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    public List<String> handle(){
        double allIndividuals = rebelRepository.findAll().size();
        double traitors = rebelRepository.findAll().stream().filter(Rebel::isTraitor).count();
        double traitorsDecimal = traitors / allIndividuals;
        double alliesDecimal = 1 - traitorsDecimal;
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        String traitorPercentage = numberFormat.format(traitorsDecimal);
        String alliesPercentage = numberFormat.format(alliesDecimal);

        return new ArrayList<>(Arrays.asList(alliesPercentage, traitorPercentage));
    }
}
