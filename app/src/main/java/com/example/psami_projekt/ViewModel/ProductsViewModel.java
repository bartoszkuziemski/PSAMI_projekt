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

    private DatabaseHelper databaseHelper;
    private MutableLiveData<ArrayList<ProductInMeal>> mutableLiveData;

    public ProductsViewModel() {
    }

    public ProductsViewModel(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public void init(Context context) {
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

    public boolean deleteFromDatabase(int id) {
        return databaseHelper.deleteProduct(id);
    }

    public LiveData<ArrayList<ProductInMeal>> getProductsFromMeal(String date, String meal) {
        //ArrayList<ProductInMeal> products = new ArrayList<>();
        mutableLiveData = databaseHelper.getProductsFromMeal(date, meal);
        //products = databaseHelper.getProductsFromMeal(date, meal);
        return mutableLiveData;
    }

    public boolean addProductToMeal(String date, String meal, int productId, Integer grams) {
        if (productId > 0 && date != "" && meal != "") {
            return databaseHelper.addProductToMeal(date, meal, productId, grams);
        }
        return false;
    }

    public boolean deleteProductFromMeal(ProductInMeal productInMeal, String date, String meal) {
        if(databaseHelper.deleteProductFromMeal(productInMeal.getId())) {
            //mutableLiveData = databaseHelper.getProductsFromMeal(date, meal);
            ArrayList<ProductInMeal> products = mutableLiveData.getValue();
            products.remove(productInMeal);
            mutableLiveData.postValue(products);
            return true;
        }
        return false;
    }
}
