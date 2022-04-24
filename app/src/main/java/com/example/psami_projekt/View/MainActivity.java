package com.example.psami_projekt.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.psami_projekt.R;
import com.example.psami_projekt.View.Adapter.MealAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;

    private RecyclerView mealRecView;
    private MealAdapter mealAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mealAdapter = new MealAdapter(this);
        mealRecView.setAdapter(mealAdapter);
        mealRecView.setLayoutManager(new LinearLayoutManager(this));
        //// TODO: 25.04.2022 adapter.setMeals , probably with ViewModel

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mealPlan:
                        Toast.makeText(MainActivity.this, "meal plan clicked", Toast.LENGTH_SHORT).show();
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

    private void initViews() {
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        mealRecView = findViewById(R.id.mealRecView);
    }
}