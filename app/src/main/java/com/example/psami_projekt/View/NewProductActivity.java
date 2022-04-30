package com.example.psami_projekt.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.R;
import com.example.psami_projekt.ViewModel.ProductsViewModel;

public class NewProductActivity extends AppCompatActivity {

    private EditText editTextName, editTextDescription, editTextProtein, editTextFat, editTextCarbs;
    private Button btnNewProduct;
    private TextView txtAlert;
    private ProductsViewModel productsViewModel = new ProductsViewModel(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        initViews();


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
                    //productsViewModel.addToDatabase(newProduct);
                    Toast.makeText(NewProductActivity.this, "new product added", Toast.LENGTH_SHORT).show();
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
        txtAlert.setVisibility(View.GONE);
    }

    private void checkEditText(EditText editText) {
        if (editText.getText().toString().equals("")) {
            txtAlert.setVisibility(View.VISIBLE);
        }
    }
}