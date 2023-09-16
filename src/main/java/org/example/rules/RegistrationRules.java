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
                genericRules.handle(location.getLatitude(), 180),
                genericRules.handle(location.getLongitude(), 90),
                genericRules.handle(location.getBase())
        );

        List<Item> fInvList = new ArrayList<>();

        for (Item i: inventory.getInvList()) {
            fInvList.add(
                    new Item(
                            genericRules.handle(i.getName()),
                            genericRules.handle(i.getQuantity()),
                            genericRules.handle(i.getPrice()))
            );
        }
        Inventory fInventory = new Inventory(rebel.getId(), fInvList);

        return new ArrayList<>(Arrays.asList(fRebel, fInventory, fLocation));
    }
}
