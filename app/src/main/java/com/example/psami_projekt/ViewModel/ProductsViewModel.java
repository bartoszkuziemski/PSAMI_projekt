package com.example.psami_projekt.ViewModel;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.psami_projekt.Model.DatabaseHelper;
import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.Model.ProductInMeal;
import com.example.psami_projekt.View.ProductActivity;

import java.util.ArrayList;

public class ProductsViewModel extends ViewModel {

    private final DatabaseHelper databaseHelper;

    public ProductsViewModel(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public ArrayList<Product> getStaringProducts() {
        ArrayList<Product> startingProducts;
        startingProducts = databaseHelper.initStartingProducts();
        return startingProducts;
    }

    public ArrayList<Product> getProductsByName(String productName) {
        ArrayList<Product> searchedProducts;
        searchedProducts = databaseHelper.getProductsByName(productName);
        return searchedProducts;
    }

    public Product getProductById(int id) {
        return databaseHelper.getProductById(id);
    }

    public boolean addToDatabase(Product product) {
        if (product != null) {
            return databaseHelper.addProduct(product);
        }
        return false;
    }

    public boolean deleteFromDatabase(int id) {
        return databaseHelper.deleteProduct(id);
    }

    public ArrayList<ProductInMeal> getProductsFromMeal(String date, String meal) {
        ArrayList<ProductInMeal> products;
        products = databaseHelper.getProductsFromMeal(date, meal);
        return products;
    }

    public ArrayList<ProductInMeal> getProductsFromDay(String date) {
        return databaseHelper.getProductsFromDay(date);
    }

    public boolean addProductToMeal(String date, String meal, int productId, Integer grams) {
        if (productId > 0 && date != "" && meal != "") {
            return databaseHelper.addProductToMeal(date, meal, productId, grams);
        }
        return false;
    }

    public boolean deleteProductFromMeal(int productId) {
        return databaseHelper.deleteProductFromMeal(productId);
    }
}
