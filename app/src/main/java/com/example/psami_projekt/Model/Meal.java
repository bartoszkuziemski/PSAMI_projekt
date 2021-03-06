package com.example.psami_projekt.Model;

import java.util.ArrayList;

public class Meal {

    private String name;
    private Integer kcal;
    private Double proteins, fats, carbs;
    ArrayList<Product> products;
    private static ArrayList<Meal> meals;

    public Meal(String name) {
        this(name, 0, 0.0, 0.0, 0.0);
    }

    public Meal(String name, Integer kcal, Double proteins, Double fats, Double carbs) {
        this.name = name;
        this.kcal = kcal;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }

    public static void initMeals() {
        if (meals == null) {
            meals = new ArrayList<>();
            meals.add(new Meal("Breakfast"));
            meals.add(new Meal("Lunch"));
            meals.add(new Meal("Dinner"));
            meals.add(new Meal("Snack"));
            meals.add(new Meal("Supper"));
        }
    }

    public static ArrayList<Meal> getMeals() {
        return meals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
