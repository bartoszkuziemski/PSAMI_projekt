package com.example.psami_projekt.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

public final class Utils {

    private static final String MAX_KCAL = "max_kcal";
    private static final String MAX_PROTEINS = "max_proteins";
    private static final String MAX_FATS = "max_fats";
    private static final String MAX_CARBS = "max_carbs";

    private SharedPreferences sharedPreferences;
    private static Utils instance;

    private static String date;
    private static String meal;



    private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("max_kcal", Context.MODE_PRIVATE);
    }

    public static Utils getInstance(Context context) {
        if (instance == null) {
            instance = new Utils(context);
        }
        return instance;
    }

    public Integer getMaxKcal() {
        return sharedPreferences.getInt(MAX_KCAL, 3000);
    }

    public void setMaxKcal(Integer maxKcal) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(MAX_KCAL, maxKcal);
        editor.commit();
    }

    public Integer getMaxProteins() {
        return sharedPreferences.getInt(MAX_PROTEINS, 150);
    }

    public void setMaxProteins(Integer maxProteins) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(MAX_PROTEINS, maxProteins);
        editor.commit();
    }

    public Integer getMaxFats() {
        return sharedPreferences.getInt(MAX_FATS, 100);
    }

    public void setMaxFats(Integer maxFats) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(MAX_FATS, maxFats);
        editor.commit();
    }

    public Integer getMaxCarbs() {
        return sharedPreferences.getInt(MAX_CARBS, 380);
    }

    public void setMaxCarbs(Integer maxCarbs) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(MAX_CARBS, maxCarbs);
        editor.commit();
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
