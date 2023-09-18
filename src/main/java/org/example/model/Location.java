package org.example.model;

import org.example.repositories.GenericEntity;

import java.util.UUID;

public class Location extends GenericEntity {
    private UUID uuid = UUID.randomUUID();
    private Double latitude;
    private Double longitude;
    private String base;

    public Location(Double latitude, Double longitude, String base) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.base = base;
    }

    public Location() {
    }

    public void setNewLocation(Location newLocation) {
        this.latitude = newLocation.latitude;
        this.longitude = newLocation.longitude;
        this.base = newLocation.base;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getBase() {
        return base;
    }

    @Override
    public String toString() {
        return "Location{" + "UUID=" + uuid + ", latitude=" + latitude + ", longitude=" + longitude + ", base='" + base + '\'' + '}';
    }
}
