package org.example.request;

import org.example.model.Location;

public record RequestLocationUpdate(String rebelName, Location newLocation) {
}
