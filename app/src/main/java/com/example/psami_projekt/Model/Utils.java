package com.example.psami_projekt.Model;

import java.util.ArrayList;

public class Utils {

    private static ArrayList<Meal> meals;
    private static ArrayList<Product> products;

    public static void initMeals() {
        if (meals == null) {
            meals = new ArrayList<>();

            Meal breakfast = new Meal("breakfast");
            Meal lunch = new Meal("lunch");

            meals.add(breakfast);
            meals.add(lunch);
        }
    }

    public static ArrayList<Meal> getMeals() {
        return meals;
    }

}
