package com.example.psami_projekt.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.psami_projekt.Model.Meal;
import com.example.psami_projekt.Model.Utils;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.Adapter.MealAdapter;
import com.example.psami_projekt.View.Adapter.ProductInMealAdapter;
import com.example.psami_projekt.ViewModel.ProductsViewModel;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private String date;
    private ProductsViewModel productsViewModel = new ProductsViewModel(this);

    private RecyclerView mealRecView;
    private MealAdapter mealAdapter;

    // tests
    private ProductInMealAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        /**
         * Get day id from adapter
         */
        Intent intent = getIntent();
        if (intent.getStringExtra(CalendarActivity.DATE_ID_KEY) != null) {
            date = intent.getStringExtra(CalendarActivity.DATE_ID_KEY);
        } else {
            date = LocalDate.now().toString();
        }

        Utils.setDate(date);

        mealRecView = findViewById(R.id.mealRecView);
        mealAdapter = new MealAdapter(this);
        mealRecView.setAdapter(mealAdapter);
        mealRecView.setLayoutManager(new LinearLayoutManager(this));
        Meal.initMeals();
        ArrayList<Meal> meals = Meal.getMeals();
        if (meals != null) {
            mealAdapter.setMeals(meals);
        }

//         tests
//        recyclerView = findViewById(R.id.mealRecView);
//        adapter = new ProductInMealAdapter(this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        ArrayList<Product> products = productsViewModel.getStaringProducts();
//        adapter.setProducts(products);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //// TODO: 12.05.2022 set activities
                switch (item.getItemId()) {
                    case R.id.mealPlan:
                        Toast.makeText(MainActivity.this, "meal plan clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.calendar:
                        Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.recipes:

                        break;
                    case R.id.summary:

                        break;
                    case R.id.bodyMeasurements:

                        break;
                    case R.id.settings:

                        break;
                    default:
                        break;
                }
                return false;
            }
        });



        //productsViewModel.addProductToMeal(11, "2022-05-14", "Lunch");

       // ArrayList<Product> products = productsViewModel.getProductsFromMeal("aaa", "bbb");

       // ArrayList<Product> testProducts = productsViewModel.getStaringProducts();

    }

    private void initViews() {
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
    }

}