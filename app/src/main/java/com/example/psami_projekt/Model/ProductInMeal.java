package com.example.psami_projekt.Model;

public class ProductInMeal extends Product {

    private Integer idInMealDB;
    private Integer grams;

    public ProductInMeal() {
    }

    public Integer getIdInMealDB() {
        return idInMealDB;
    }

    public void setIdInMealDB(Integer idInMealDB) {
        this.idInMealDB = idInMealDB;
    }

    public Integer getGrams() {
        return grams;
    }

    public void setGrams(Integer grams) {
        this.grams = grams;
    }
}
