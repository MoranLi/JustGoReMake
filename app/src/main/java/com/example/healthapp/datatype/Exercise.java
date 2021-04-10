package com.example.healthapp.datatype;

public class Exercise {
    private int id;
    private String name;
    private int category;
    private double energy_consumption;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public double getEnergy_consumption() {
        return energy_consumption;
    }

    public void setEnergy_consumption(double energy_consumption) {
        this.energy_consumption = energy_consumption;
    }
}
