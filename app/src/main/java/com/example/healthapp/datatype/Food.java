package com.example.healthapp.datatype;

/**
 * food type
 */
public class Food {
    private int id;
    private int userId;
    private int category;
    private String name;
    private double protein;
    private double fat;
    private double cholesterol;
    private double calories;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public double getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", protein=" + protein +
                ", fat=" + fat +
                ", cholesterol=" + cholesterol +
                ", calories=" + calories +
                '}';
    }
}
