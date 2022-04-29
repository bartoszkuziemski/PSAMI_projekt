package com.example.psami_projekt.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView btnSearch;
    private ArrayList<Product> searchedProducts = new ArrayList<>();

    // tests
    private TextView txtTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();

        searchAdapter = new SearchAdapter(this);
        searchRecView.setAdapter(searchAdapter);
        searchRecView.setLayoutManager(new LinearLayoutManager(this));

        /**
         * init some products at the beginning
         */
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        searchedProducts = databaseHelper.loadHandler();
        if (searchedProducts != null) {
            searchAdapter.setSearchedProducts(searchedProducts);
        }


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSearch();
            }
        });

        /**
         * init search db after text in searchBox has changed
         */
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                initSearch();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * get text from searchBox and get products from db by name
     */
    private void initSearch() {
        if (!searchBox.getText().toString().equals("")) {
            String productName = searchBox.getText().toString();
            //// TODO: 27.04.2022 get items, change to ViewModel

            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            searchedProducts = databaseHelper.getProductsByName(productName);

            if (searchedProducts != null) {
                searchAdapter.setSearchedProducts(searchedProducts);
            }

        }
    }

    private void initViews() {
        searchRecView = findViewById(R.id.searchRecView);
        searchBox = findViewById(R.id.searchBox);
        btnSearch = findViewById(R.id.btnSearch);
    }
}