package com.example.psami_projekt.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.psami_projekt.Model.DatabaseHelper;
import com.example.psami_projekt.Model.Product;

import java.util.ArrayList;

public class ProductsViewModel extends ViewModel {

    private DatabaseHelper databaseHelper;
    private Context context;

    public ProductsViewModel(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    public void setContext(Context context) {
        this.context = context;
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
}
