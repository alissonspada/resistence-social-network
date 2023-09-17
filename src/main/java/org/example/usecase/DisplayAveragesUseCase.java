package org.example.usecase;

import org.example.model.Item;
import org.example.repositories.InventoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayAveragesUseCase {
    private final InventoryRepository inventoryRepository;

    public DisplayAveragesUseCase(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public String handle() {
        List<Item> allItems = inventoryRepository.findAll().stream().flatMap(inv -> inv.getItemList().stream()).toList();
        Map<String, Integer> totalEach = new HashMap<>();
        StringBuilder result = new StringBuilder();
        for (Item i : allItems) {
            totalEach.put(i.getName(), totalEach.getOrDefault(i.getName(), 0) + i.getQuantity());
        }
        for (Map.Entry<String, Integer> entry : totalEach.entrySet()) {
            Integer average = entry.getValue() / inventoryRepository.findAll().size();
            totalEach.put(entry.getKey(), average);
            result.append(entry.getKey()).append(" ").append(average).append(" ");
        }
        return result.toString();
    }
}
