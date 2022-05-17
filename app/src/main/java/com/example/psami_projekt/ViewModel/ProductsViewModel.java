package com.example.psami_projekt.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.psami_projekt.Model.DatabaseHelper;
import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.View.ProductActivity;

import java.util.ArrayList;

public class ProductsViewModel extends ViewModel {

    private DatabaseHelper databaseHelper;

    public ProductsViewModel(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public ArrayList<Product> getStaringProducts() {
        ArrayList<Product> startingProducts = new ArrayList<>();
        startingProducts = databaseHelper.initStartingProducts();
        return startingProducts;
    }

    public ArrayList<Product> getProductsByName(String productName) {
        ArrayList<Product> searchedProducts = new ArrayList<>();
        searchedProducts = databaseHelper.getProductsByName(productName);
        return searchedProducts;
    }

    public Product getProductById(int id) {
        Product product = databaseHelper.getProductById(id);
        return product;
    }

    public boolean addToDatabase(Product product) {
        if (product != null) {
            return databaseHelper.addProduct(product);
        }
        return false;
    }

    public ArrayList<Product> getProductsFromMeal(String date, String meal) {
        ArrayList<Product> products = new ArrayList<>();
        products = databaseHelper.getProductsFromMeal(date, meal);
        return products;
    }

    public boolean addProductToMeal(int productId, String date, String meal) {
        if (productId > 0 && date != "" && meal != "") {
            return databaseHelper.addProductToMeal(productId, date, meal);
        }
        return false;
    }

    public boolean deleteFromDatabase(int id) {
        return databaseHelper.deleteProduct(id);
    }

}
