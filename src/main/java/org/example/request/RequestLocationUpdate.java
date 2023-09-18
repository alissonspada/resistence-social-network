package org.example.request;

import org.example.model.Location;

import java.util.UUID;

public record RequestLocationUpdate(UUID locationId, Location newLocation) {
}
