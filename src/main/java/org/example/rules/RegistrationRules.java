package org.example.rules;

import org.example.model.Inventory;
import org.example.model.Item;
import org.example.model.Location;
import org.example.model.Rebel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegistrationRules {
    public List<?> format(Rebel rebel, Location location, Inventory inventory) {
        GenericRules genericRules = new GenericRules();

        rebel.setName(genericRules.handle(rebel.getName()));
        rebel.setGender(genericRules.handle(rebel.getGender()));
        rebel.setAge(genericRules.handle(rebel.getAge()));

        location.setLatitude(genericRules.handle(location.getLatitude(), 90));
        location.setLongitude(genericRules.handle(location.getLongitude(), 180));
        location.setBase(genericRules.handle(location.getBase()));

        List<Item> fInventoryList = new ArrayList<>();

        for (Item i: inventory.getItemList()) {
            if (fInventoryList // Adds Item to list if there isn't another Item in it with the same name
                    .stream()
                    .map(item -> item.getName().equals(i.getName()))
                    .findFirst()
                    .isEmpty()) {
                fInventoryList.add(
                        new Item(
                                genericRules.handle(i.getName()),
                                genericRules.handle(i.getQuantity()),
                                genericRules.handle(i.getPrice()))
                );
            } else { // Overrides quantity of previously added same-name Item with current quantity
                fInventoryList.stream()
                        .filter(item -> item.getName().equals(i.getName()))
                        .findFirst()
                        .get()
                        .setQuantity(i.getQuantity());
            }
        }
        inventory.setNewItemList(fInventoryList);

        return new ArrayList<>(Arrays.asList(rebel, location, inventory));
    }
}
