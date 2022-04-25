package com.example.psami_projekt.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psami_projekt.Model.Meal;
import com.example.psami_projekt.Model.Utils;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.Adapter.MealAdapter;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private RecyclerView mealRecView;
    private MealAdapter mealAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);

        mealAdapter = new MealAdapter(getContext());
        mealRecView.setAdapter(mealAdapter);
        mealRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        //// TODO: 25.04.2022 adapter.setMeals , probably with ViewModel
        ArrayList<Meal> meals = Utils.getMeals();
        if (meals != null) {
            mealAdapter.setMeals(meals);
        }

        return view;
    }

    private void initViews(View view) {
        mealRecView = view.findViewById(R.id.mealRecView);
    }
}
