package com.example.psami_projekt.View;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.Model.Utils;
import com.example.psami_projekt.R;
import com.example.psami_projekt.ViewModel.ProductsViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductActivity extends BaseActivity {

    public static final String PRODUCT_ID_KEY = "productId";
    public static final String PRODUCT_IN_MEAL_ID_KEY = "productInMealId";
    private final ProductsViewModel productsViewModel = new ProductsViewModel(this);
    private int id;
    private int idInMealDB;

    private TextView name, description, kcal, protein, fat, carbs;
    private FloatingActionButton fabAddProduct;
    private EditText editTextHowManyGrams;
    private Button btnDelete;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_product, contentFrameLayout);

        initViews();
        super.setToolbar(this, toolbar, "Add product to meal");
        setFieldsFromIntent();
        setProduct();
        setAddProductListener();
        setBtnDeleteListener();
    }

    private void setFieldsFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            this.id = intent.getIntExtra(PRODUCT_ID_KEY, -1);
            this.idInMealDB = intent.getIntExtra(PRODUCT_IN_MEAL_ID_KEY, -1);
        }
    }

    /**
     * Set product fields by getting id from intent and querying database
     */
    private void setProduct() {
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

    /**
     * After click get grams from editText
     * call addProduct or editProduct accordingly to intent get arguments,
     * start MainActivity
     */
    private void setAddProductListener() {
        fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editTextHowManyGrams.getText().toString().equals("")) {
                    Integer grams = Integer.valueOf(editTextHowManyGrams.getText().toString());
                    if (ProductActivity.this.idInMealDB == -1) {
                        productsViewModel.addProductToMeal(Utils.getDate(), Utils.getMeal(), ProductActivity.this.id, grams);
                    } else {
                        productsViewModel.editProductInMeal(ProductActivity.this.idInMealDB, grams);
                    }
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    mainIntent.putExtra(CalendarActivity.DATE_ID_KEY, Utils.getDate());
                    startActivity(mainIntent);
                    finishAffinity();
                }
            }
        });
    }

    private void setBtnDeleteListener() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
                builder.setMessage("Are you sure you want to delete this product?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (productsViewModel.deleteFromDatabase(ProductActivity.this.id)) {
                            Toast.makeText(ProductActivity.this, "Product deleted successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ProductActivity.this, "Error during deleting product", Toast.LENGTH_SHORT).show();
                        }
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
        toolbar = findViewById(R.id.productActivityToolbar);
    }
}