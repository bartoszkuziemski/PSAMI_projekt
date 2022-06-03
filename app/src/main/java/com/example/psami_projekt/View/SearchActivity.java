package com.example.psami_projekt.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.Adapter.SearchAdapter;
import com.example.psami_projekt.ViewModel.ProductsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private final ProductsViewModel productsViewModel = new ProductsViewModel(this);
    private ArrayList<Product> searchedProducts = new ArrayList<>();
    private RecyclerView searchRecView;
    private SearchAdapter searchAdapter;
    private EditText searchBox;
    private ImageView btnSearch;
    private ConstraintLayout addNewProduct;
    private FloatingActionButton fabAddNewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();
        initRecyclerView();
        setAddNewProductListeners();
        initStartingProducts();
        setSearchListeners();
    }

    /**
     * init some products at the beginning
     */
    private void initStartingProducts() {
        searchedProducts = productsViewModel.getStaringProducts();
        if (searchedProducts != null) {
            searchAdapter.setSearchedProducts(searchedProducts);
        }
    }

    /**
     * init search db after text in searchBox has changed
     */
    private void setSearchListeners() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSearch();
            }
        });

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                initSearch();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    /**
     * Go to newProductActivity by addNewProduct ConstraintLayout or fabAddNewProduct
     */
    private void setAddNewProductListeners() {
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, NewProductActivity.class);
                startActivity(intent);
            }
        });
        fabAddNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, NewProductActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * get text from searchBox and get products from db by name
     */
    private void initSearch() {
        if (!searchBox.getText().toString().equals("")) {
            String productName = searchBox.getText().toString();
            searchedProducts = productsViewModel.getProductsByName(productName);

            if (searchedProducts != null) {
                searchAdapter.setSearchedProducts(searchedProducts);
            }
        }
    }

    private void initViews() {
        searchRecView = findViewById(R.id.searchRecView);
        searchBox = findViewById(R.id.searchBox);
        btnSearch = findViewById(R.id.btnSearch);
        addNewProduct = findViewById(R.id.addNewProduct);
        fabAddNewProduct = findViewById(R.id.fabNewProduct);
    }

    private void initRecyclerView() {
        searchAdapter = new SearchAdapter(this);
        searchRecView.setAdapter(searchAdapter);
        searchRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initSearch();
    }

}