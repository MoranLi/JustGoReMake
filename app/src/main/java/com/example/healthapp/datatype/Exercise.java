package com.example.healthapp.datatype;

/**
 * exercise type
 */
public class Exercise {
    private int id;
    private String name;
    private int category;
    private double energyConsumption;

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

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(double energy_consumption) {
        this.energyConsumption = energy_consumption;
    }
}
