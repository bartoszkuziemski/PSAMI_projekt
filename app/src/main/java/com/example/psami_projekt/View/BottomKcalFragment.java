package com.example.psami_projekt.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.psami_projekt.Model.Meal;
import com.example.psami_projekt.R;

import java.util.ArrayList;

public class BottomKcalFragment extends Fragment {

    TextView txtKcal, txtProtein, txtFats, txtCarbs;
    TextView txtMaxKcal, txtMaxProtein, txtMaxFats, txtMaxCarbs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_kcal, container, false);

        initTxtFields(view);

        ArrayList<Meal> meals = Meal.getMeals();
        Integer kcal = 0;
        Double protein = 0.0, fats = 0.0, carbs = 0.0;
        for (Meal meal : meals) {
            kcal += meal.getKcal();
            protein += meal.getProteins();
            fats += meal.getFats();
            carbs += meal.getCarbs();
        }
        //txtKcal.setText();
        // TODO: 30.05.2022 finish setting txtFields 

        return view;
    }

    private void initTxtFields(View view) {
        txtKcal = view.findViewById(R.id.txtBottomKcal);
        txtProtein = view.findViewById(R.id.txtBottomProtein);
        txtFats = view.findViewById(R.id.txtBottomFats);
        txtCarbs = view.findViewById(R.id.txtBottomCarbs);
        txtMaxKcal = view.findViewById(R.id.txtMaxKcal);
        txtMaxProtein = view.findViewById(R.id.txtMaxProtein);
        txtMaxFats = view.findViewById(R.id.txtMaxFats);
        txtMaxCarbs = view.findViewById(R.id.txtMaxCarbs);
    }
}