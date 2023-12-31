package org.example.model;

import org.example.repositories.GenericEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Rebel extends GenericEntity {
    private UUID uuid = UUID.randomUUID();
    private String name;
    private Integer age;
    private String gender;
    private Integer reportCounter;
    private List<UUID> reportedRebels;

    public Rebel(String name, Integer age, String gender) {
        this.reportedRebels = new ArrayList<>();
        this.reportCounter = 0;
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

    public UUID getId() {
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
        return "Rebel{" + "UUID=" + uuid + ", name='" + name + '\'' + ", age=" + age + ", gender='" + gender + '\'' + '}';
    }
}
