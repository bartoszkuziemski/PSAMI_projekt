package com.example.psami_projekt.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.R;
import com.example.psami_projekt.ViewModel.ProductsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductActivity extends AppCompatActivity {

    public static final String PRODUCT_ID_KEY = "productId";
    private TextView name, description, kcal, protein, fat, carbs;
    private FloatingActionButton fabAddProduct;
    private EditText editTextHowManyGrams;
    private ProductsViewModel productsViewModel = new ProductsViewModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initViews();

        Intent intent = getIntent();
        if (intent != null) {
            int productId = intent.getIntExtra(PRODUCT_ID_KEY, -1); // get id of product;
            if (productId != -1) {
                Product incomingProduct = productsViewModel.getProductById(productId);
                if (incomingProduct != null) {
                    name.setText(incomingProduct.getName());
                    description.setText(incomingProduct.getDescription());
                    kcal.setText(String.valueOf(incomingProduct.getKcal()));
                    protein.setText(String.valueOf(incomingProduct.getProtein()));
                    fat.setText(String.valueOf(incomingProduct.getFat()));
                    carbs.setText(String.valueOf(incomingProduct.getCarbs()));
                }
            }
        }
    }

    private void initViews() {
        name = findViewById(R.id.txtProductActivityName);
        description = findViewById(R.id.txtProductActivityDesc);
        kcal = findViewById(R.id.txtProductActivityKcal);
        protein = findViewById(R.id.txtProductActivityProtein);
        fat = findViewById(R.id.txtProductActivityFat);
        carbs = findViewById(R.id.txtProductActivityCarbs);
        fabAddProduct = findViewById(R.id.fabAddProductActivity);
        editTextHowManyGrams = findViewById(R.id.editTextHowManyGrams);
    }
}