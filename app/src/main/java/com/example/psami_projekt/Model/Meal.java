package com.example.psami_projekt.Model;

import java.util.ArrayList;

public class Meal {

    private String name;
    private Integer kcal, proteins, fats, carbs;
    ArrayList<Product> products;

    public Meal(String name, Integer kcal, Integer proteins, Integer fats, Integer carbs) {
        this.name = name;
        this.kcal = kcal;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
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

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer getFats() {
        return fats;
    }

    public void setFats(Integer fats) {
        this.fats = fats;
    }

    public Integer getCarbs() {
        return carbs;
    }

    public void setCarbs(Integer carbs) {
        this.carbs = carbs;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
