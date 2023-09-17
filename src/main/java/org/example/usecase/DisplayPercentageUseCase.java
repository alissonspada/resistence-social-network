package org.example.usecase;

import org.example.model.Rebel;
import org.example.repositories.RebelRepository;

import java.text.NumberFormat;

public class DisplayPercentageUseCase {
    private final RebelRepository rebelRepository;

    public DisplayPercentageUseCase(RebelRepository rebelRepository) {
        this.rebelRepository = rebelRepository;
    }

    public String handle(){
        double allIndividuals = rebelRepository.findAll().size();
        double traitors = rebelRepository.findAll().stream().filter(Rebel::isTraitor).count();
        double traitorsDecimal = traitors / allIndividuals;
        double alliesDecimal = 1 - traitorsDecimal;
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        String traitorPercentage = numberFormat.format(traitorsDecimal);
        String alliesPercentage = numberFormat.format(alliesDecimal);

        return "Allies: " + alliesPercentage + " " + "Traitors: " + traitorPercentage;
    }
}
