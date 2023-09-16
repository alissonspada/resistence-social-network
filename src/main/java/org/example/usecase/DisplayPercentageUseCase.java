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
        double traitors = rebelRepository.findAll().stream().filter(Rebel::isTraitor).count();
        double total = rebelRepository.findAll().size();
        double traitorDecimal = traitors / total;
        double allianceDecimal = 1 - traitorDecimal;
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        String traitorPercentage = numberFormat.format(traitorDecimal);
        String alliancePercentage = numberFormat.format(allianceDecimal);

        return "Alliance: " + alliancePercentage + " " + "Traitors: " + traitorPercentage;
    }
}
