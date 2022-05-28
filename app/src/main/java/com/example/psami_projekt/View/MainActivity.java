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
import com.example.psami_projekt.Model.ProductInMeal;
import com.example.psami_projekt.Model.Utils;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.Adapter.MealAdapter;
import com.example.psami_projekt.View.Adapter.ProductInMealAdapter;
import com.example.psami_projekt.ViewModel.ProductsViewModel;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MealAdapter.OnMealRecyclerListener {

    private final ProductsViewModel productsViewModel = new ProductsViewModel(this);
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private RecyclerView mealRecView;
    private MealAdapter mealAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        getDateFromCalendar();

        initMealRecView();

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

    }

    private void initMealRecView() {
        mealRecView = findViewById(R.id.mealRecView);
        mealAdapter = new MealAdapter(this);
        mealAdapter.setOnMealRecyclerListener(this);
        mealRecView.setAdapter(mealAdapter);
        mealRecView.setLayoutManager(new LinearLayoutManager(this));
        Meal.initMeals();
        ArrayList<Meal> meals = Meal.getMeals();
        if (meals != null) {
            mealAdapter.setMeals(meals);
        }
    }

    private void initViews() {
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
    }

    /**
     * Get date from calendar, if there is none set today's date
     */
    private void getDateFromCalendar() {
        Intent intent = getIntent();
        String date;
        if (intent.getStringExtra(CalendarActivity.DATE_ID_KEY) != null) {
            date = intent.getStringExtra(CalendarActivity.DATE_ID_KEY);
        } else {
            date = LocalDate.now().toString();
        }
        Utils.setDate(date);
    }

    @Override
    public void deleteProduct(int productId, int mealPosition) {
        productsViewModel.deleteProductFromMeal(productId);
        mealAdapter.notifyDataSetChanged();
        mealAdapter.getProductInMealAdapter().notifyDataSetChanged();
    }
}