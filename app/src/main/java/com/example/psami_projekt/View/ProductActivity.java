package com.example.psami_projekt.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.R;
import com.example.psami_projekt.ViewModel.ProductsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductActivity extends AppCompatActivity {

    public static final String PRODUCT_ID_KEY = "productId";
    private TextView name, description, kcal, protein, fat, carbs;
    private FloatingActionButton fabAddProduct;
    private EditText editTextHowManyGrams;
    private Button btnDelete;
    private int id;
    private ProductsViewModel productsViewModel = new ProductsViewModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initViews();

        Intent intent = getIntent();
        if (intent != null) {
            // int productId = intent.getIntExtra(PRODUCT_ID_KEY, -1); // get id of product;
            this.id = intent.getIntExtra(PRODUCT_ID_KEY, -1);  // get id of product;
            if (this.id != -1) {
                Product incomingProduct = productsViewModel.getProductById(this.id);
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

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
                builder.setMessage("Are you sure you want to delete this product?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //// TODO: 04.05.2022 delete from db

                        if (productsViewModel.deleteFromDatabase(ProductActivity.this.id)) {
                            Toast.makeText(ProductActivity.this, "Product deleted successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ProductActivity.this, "Error during deleting product", Toast.LENGTH_SHORT).show();
                        }
                        //notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // nothing
                    }
                });
                builder.create().show();
            }
        });
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
        btnDelete = findViewById(R.id.btnDelete);
    }
}