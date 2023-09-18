package org.example.request;

import org.example.model.Inventory;
import org.example.model.Location;
import org.example.model.Rebel;

public record SignUpRequest(Rebel rebel, Location location, Inventory inventory) {
}
