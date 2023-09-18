package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.example.repositories.GenericEntity;

import java.util.UUID;

@Entity
public class Location extends GenericEntity {
    @Id
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

    public void setLatitude(Double newLatitude) {
        latitude = newLatitude;
    }
    public void setLongitude(Double newLongitude){
        longitude = newLongitude;
    }
    public void setBase(String newBase) {
        base = newBase;
    }
    @Override
    public String toString() {
        return "Location{" + "UUID=" + uuid + ", latitude=" + latitude + ", longitude=" + longitude + ", base='" + base + '\'' + '}';
    }
}
