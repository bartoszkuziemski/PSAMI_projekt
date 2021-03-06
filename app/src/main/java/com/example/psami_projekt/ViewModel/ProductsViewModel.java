package com.example.psami_projekt.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.psami_projekt.Model.DatabaseHelper;
import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.Model.ProductBase;
import com.example.psami_projekt.Model.ProductInMeal;

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

    public ProductBase getKcalFromDay(String date) {
        return databaseHelper.getKcalFromDay(date);
    }

    public boolean addProductToMeal(String date, String meal, int productId, Integer grams) {
        return databaseHelper.addProductToMeal(date, meal, productId, grams);
    }

    public boolean deleteProductFromMeal(int productId) {
        return databaseHelper.deleteProductFromMeal(productId);
    }

    public void editProductInMeal(int idInMealDB, Integer grams) {
        databaseHelper.editProductInMeal(idInMealDB, grams);
    }

}
