package com.example.psami_projekt.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;

import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.R;
import com.example.psami_projekt.ViewModel.ProductsViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Objects;

public class NewProductActivity extends BaseActivity {

    private final ProductsViewModel productsViewModel = new ProductsViewModel(this);
    private EditText editTextName, editTextDescription, editTextProtein, editTextFat, editTextCarbs;
    private Button btnNewProduct;
    private TextView txtAlert;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_new_product, contentFrameLayout);

        initViews();
        super.setToolbar(this, toolbar, "Add new product");
        setOnClickListeners();

    }

    private void setOnClickListeners() {
        btnNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextName.getText().toString().equals("") || editTextDescription.getText().toString().equals("") || editTextProtein.getText().toString().equals("") ||
                        editTextFat.getText().toString().equals("") || editTextCarbs.getText().toString().equals("")) {
                    txtAlert.setVisibility(View.VISIBLE);
                } else {
                    txtAlert.setVisibility(View.GONE);
                    String name = editTextName.getText().toString();
                    String description = editTextDescription.getText().toString();
                    double protein = Double.parseDouble(editTextProtein.getText().toString());
                    double fat = Double.parseDouble(editTextFat.getText().toString());
                    double carbs = Double.parseDouble(editTextCarbs.getText().toString());
                    Product newProduct = new Product(name, description, protein, fat, carbs);
                    if (productsViewModel.addToDatabase(newProduct)) {
                        Toast.makeText(NewProductActivity.this, "New product added successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NewProductActivity.this, "Cannot add a product", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initViews() {
        editTextName = findViewById(R.id.newProductName);
        editTextDescription = findViewById(R.id.newProductDesc);
        editTextProtein = findViewById(R.id.newProductProtein);
        editTextFat = findViewById(R.id.newProductFat);
        editTextCarbs = findViewById(R.id.newProductCarbs);
        btnNewProduct = findViewById(R.id.btnNewProduct);
        txtAlert = findViewById(R.id.newProductAlert);
        toolbar = findViewById(R.id.newProductActivityToolbar);
        txtAlert.setVisibility(View.GONE);
    }

}