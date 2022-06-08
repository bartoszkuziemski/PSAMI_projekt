package com.example.psami_projekt.Model;

public class Product extends ProductBase {

    private int id;
    private String name, description;

    public Product() {

    }

    public Product(String name, String description, double protein, double fat, double carbs) {
        super(protein, fat, carbs);
        this.name = name;
        this.description = description;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

