package com.example.healthapp.datatype;

public class Diet {
    private int id;
    private int food_id;
    private int user_id;
    private String date;
    private Character meal_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Character getMeal_type() {
        return meal_type;
    }

    public void setMeal_type(char meal_type) {
        this.meal_type = meal_type;
    }
}
