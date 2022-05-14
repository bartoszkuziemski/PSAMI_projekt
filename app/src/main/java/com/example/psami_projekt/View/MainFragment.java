package com.example.psami_projekt.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psami_projekt.Model.Meal;
import com.example.psami_projekt.Model.MealDatabaseHelper;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.Adapter.MealAdapter;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainFragment extends Fragment {

    private RecyclerView mealRecView;
    private MealAdapter mealAdapter;
    private String dayId;

    private MealDatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);

        if (getArguments() != null) {
             dayId = getArguments().getString(CalendarActivity.DAY_ID_KEY);
             Toast.makeText(getActivity(), dayId, Toast.LENGTH_SHORT).show();
        }

        mealAdapter = new MealAdapter(getContext());
        mealRecView.setAdapter(mealAdapter);
        mealRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        //// TODO: 25.04.2022 adapter.getMeals , ViewModel
        MealDatabaseHelper.initMeals();
        ArrayList<Meal> meals = MealDatabaseHelper.getMeals();
        if (meals != null) {
            mealAdapter.setMeals(meals);
        }



        return view;
    }

    private void initViews(View view) {
        mealRecView = view.findViewById(R.id.mealRecView);
    }
}
