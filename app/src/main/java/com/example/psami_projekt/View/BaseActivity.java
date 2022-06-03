package com.example.psami_projekt.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.psami_projekt.R;
import com.google.android.material.navigation.NavigationView;

public class BaseActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initViews();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mealPlan:
                        Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentMain);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.calendar:
                        Intent intentCalendar = new Intent(getApplicationContext(), CalendarActivity.class);
                        startActivity(intentCalendar);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.recipes:

                        break;
                    case R.id.summary:

                        break;
                    case R.id.bodyMeasurements:

                        break;
                    case R.id.settings:
                        Intent intentDailyGoals = new Intent(getApplicationContext(), DailyGoalsActivity.class);
                        startActivity(intentDailyGoals);
                        drawerLayout.closeDrawers();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    private void initViews() {
        navigationView = findViewById(R.id.baseActivityNavigationView);
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }
}