package com.example.psami_projekt.Model;

public class ProductBase {

    private int kcal;
    private double protein, fat, carbs;

    public ProductBase() {
    }

    public ProductBase(double protein, double fat, double carbs) {
        this.kcal = (int) ((protein + carbs) * 4 + fat * 9);
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
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
}
