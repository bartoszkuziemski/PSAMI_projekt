package com.example.psami_projekt.Model;

public final class Utils {

    private static String date;
    private static String meal;

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
