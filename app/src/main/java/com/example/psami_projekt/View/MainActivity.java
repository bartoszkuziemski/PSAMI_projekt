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

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        getDateFromCalendar();

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.mainFragmentContainerView, MainFragment.class, null)
                .commit();


//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                //// TODO: 12.05.2022 set activities
//                switch (item.getItemId()) {
//                    case R.id.mealPlan:
//                        Toast.makeText(MainActivity.this, "meal plan clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.calendar:
//                        Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
//                        startActivity(intent);
//                        break;
//                    case R.id.recipes:
//
//                        break;
//                    case R.id.summary:
//
//                        break;
//                    case R.id.bodyMeasurements:
//
//                        break;
//                    case R.id.settings:
//
//                        break;
//                    default:
//                        break;
//                }
//                return false;
//            }
//        });

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.bottomFragmentContainerView, BottomKcalFragment.class, null)
                .commit();

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

}