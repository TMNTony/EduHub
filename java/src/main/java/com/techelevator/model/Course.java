package com.techelevator.model;

import java.math.BigDecimal;

public class Course {

    private int id;

    private String Name;
    private String description;

    private String difficulty;

    private BigDecimal cost;

    public Course(){}


    public Course(int id, String name, String difficulty, BigDecimal cost) {
        this.id = id;
        Name = name;
        this.difficulty = difficulty;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
