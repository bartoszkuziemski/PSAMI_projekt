package com.example.psami_projekt.Model;

import java.util.ArrayList;

public final class Utils {

    private static String date;
    private static String meal;
    private ArrayList<Meal> meals;

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public static String getMeal() {
        return meal;
    }

    public static void setMeal(String meal) {
        Utils.meal = meal;
    }
    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        Utils.date = date;
    }
}
