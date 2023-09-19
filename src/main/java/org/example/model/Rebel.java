package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Rebel {
    @Id
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer reportCounter = 0;
    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @OneToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
    private final List<Integer> reportedRebels = new ArrayList<>();

    public Rebel(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Rebel() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setReportCounter(Integer reportCounter) {
        this.reportCounter = reportCounter;
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

    public List<Integer> getReportedRebels() {
        return reportedRebels;
    }

    public void setReportCounterUp() {
        this.reportCounter++;
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

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Rebel { " + "name='" + name + '\'' + ", age=" + age + ", gender='" + gender + '\'' + ", reportCounter=" + reportCounter +  ", ID=" + id + " }";
    }
}
