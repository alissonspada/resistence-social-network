package org.example.rules;

import org.example.model.Inventory;
import org.example.model.Item;
import org.example.model.Location;
import org.example.model.Rebel;
import org.example.repositories.GenericEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegistrationRules {
    public List<GenericEntity> format(Rebel rebel, Location location, Inventory inventory) {
        GenericRules genericRules = new GenericRules();

        rebel.setName(genericRules.handle(rebel.getName()));
        rebel.setGender(genericRules.handle(rebel.getGender()));
        rebel.setAge(genericRules.handle(rebel.getAge()));

        location.setLatitude(genericRules.handle(location.getLatitude(), 90));
        location.setLongitude(genericRules.handle(location.getLongitude(), 180));
        location.setBase(genericRules.handle(location.getBase()));

        List<Item> fInventoryList = new ArrayList<>();

        for (Item i: inventory.getItemList()) {
            if (fInventoryList // Adds Item to list if there's no other Item in it with the same name
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
                     // e.g.: if another Item of name "food" with quantity = 4 is present,
                     // and this method tries to add another Item of same name but with quantity = 20,
                     // it will search for that previous "food" Item and set its quantity to 20.
                fInventoryList.stream()
                        .filter(item -> item.getName().equals(i.getName()))
                        .findFirst()
                        .orElseThrow()
                        .setQuantity(i.getQuantity());
            }
        }
        inventory.setNewItemList(fInventoryList);

        return new ArrayList<>(Arrays.asList(rebel, location, inventory));
    }
}
