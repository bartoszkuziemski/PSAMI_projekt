package com.example.psami_projekt.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.psami_projekt.Model.DatabaseHelper;
import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.Adapter.SearchAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView searchRecView;
    private SearchAdapter searchAdapter;
    private EditText searchBox;

    private TextView txtTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();

        // tests
        txtTest = findViewById(R.id.txtTest);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        ArrayList<Product> products = new ArrayList<>();
        products = databaseHelper.loadHandler();
        txtTest.setText(products.get(0).toString());


        searchAdapter = new SearchAdapter(this);
        searchRecView.setAdapter(searchAdapter);
        searchRecView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Product> searchedProducts = null; // get array from db
        if (searchedProducts != null) {
            searchAdapter.setSearchedProducts(searchedProducts);
        }
    }

    private void initViews() {
        searchRecView = findViewById(R.id.searchRecView);
        searchBox = findViewById(R.id.searchBox);
    }
}