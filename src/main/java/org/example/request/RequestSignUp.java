package org.example.request;

import org.example.model.Inventory;
import org.example.model.Location;
import org.example.model.Rebel;

public record RequestSignUp(Rebel rebel, Location location, Inventory inventory) {
    @Override
    public String toString() {
        return rebel + "\n" + location + "\n" + inventory;
    }
}
