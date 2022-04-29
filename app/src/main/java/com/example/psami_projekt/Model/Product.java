package com.example.psami_projekt.Model;

public class Product {

    private int id, kcal;
    private String name, description;
    private double protein, fat, carbs;

    public Product() {

    }

    public Product(String name, String description, double protein, double fat, double carbs) {
        this.kcal = (int) (fat * 9 + (carbs + protein) * 4);
        this.name = name;
        this.description = description;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", kcal=" + kcal +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbs=" + carbs +
                '}';
    }
}

