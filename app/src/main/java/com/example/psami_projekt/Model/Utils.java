package com.example.psami_projekt.Model;

import java.util.ArrayList;

public class Utils {

    private static ArrayList<Meal> meals;
    private static ArrayList<Product> products;

    public static void initMeals() {
        if (meals == null) {
            meals = new ArrayList<>();

            Meal breakfast = new Meal("breakfast", 0, 0, 0, 0);
            Meal lunch = new Meal("lunch", 0, 0, 0, 0);

            meals.add(breakfast);
            meals.add(lunch);
        }
    }

    public static ArrayList<Meal> getMeals() {
        return meals;
    }

}
