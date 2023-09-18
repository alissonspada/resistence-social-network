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

        Rebel fRebel = new Rebel(
                genericRules.handle(rebel.getName()),
                genericRules.handle(rebel.getAge()),
                genericRules.handle(rebel.getGender())
        );

        Location fLocation = new Location(
                genericRules.handle(location.getLatitude(), 90),
                genericRules.handle(location.getLongitude(), 180),
                genericRules.handle(location.getBase())
        );

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
        Inventory fInventory = new Inventory(rebel.getId(), fInventoryList);

        return new ArrayList<>(Arrays.asList(fRebel, fLocation, fInventory));
    }
}
