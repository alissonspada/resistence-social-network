package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.example.repositories.GenericEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Rebel extends GenericEntity {
    @Id
    private UUID uuid = UUID.randomUUID();
    private String name;
    private Integer age;
    private String gender;
    private Integer reportCounter = 0;
    private final List<UUID> reportedRebels = new ArrayList<>();

    public Rebel(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Rebel() {
    }
    public void setUUID(UUID newUUID) {
        uuid = newUUID;
    }
    public void setName(String newName) {
        name = newName;
    }
    public void setAge(Integer newAge) {
        age = newAge;
    }
    public void setGender(String newGender) {
        gender = newGender;
    }
    public boolean isTraitor() {
        return (reportCounter > 2);
    }

    public boolean isNotTraitor() {
        return !isTraitor();
    }

    public Integer getReportCounter() {
        return reportCounter;
    }

    public List<UUID> getReportedRebels() {
        return reportedRebels;
    }

    public void setReportCounterUp() {
        this.reportCounter++;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Rebel { " + "name='" + name + '\'' + ", age=" + age + ", gender='" + gender + '\'' + ", reportCounter=" + reportCounter +  ", UUID=" + uuid + " }";
    }
}
