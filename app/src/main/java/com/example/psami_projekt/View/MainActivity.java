package com.example.psami_projekt.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.Adapter.MealAdapter;
import com.example.psami_projekt.ViewModel.ProductsViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private String dayId;
    private ProductsViewModel productsViewModel = new ProductsViewModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

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

        /**
         * Get day id from adapter
         */
        Intent intent = getIntent();
        if (intent != null) {
            dayId = intent.getStringExtra(CalendarActivity.DAY_ID_KEY);
        }

        //productsViewModel.addProductToMeal(11, "2022-05-14", "Lunch");

        ArrayList<Product> products = productsViewModel.getProductsFromMeal("aaa", "bbb");

        /**
         * Send day id to fragment
         */
        Bundle bundle = new Bundle();
        bundle.putString(CalendarActivity.DAY_ID_KEY, dayId);
        Fragment fragment = new MainFragment();
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrameLayout, fragment);
        transaction.commit();
    }

    private void initViews() {
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
    }

}