package com.example.psami_projekt.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.psami_projekt.Model.Meal;
import com.example.psami_projekt.Model.ProductInMeal;
import com.example.psami_projekt.Model.Utils;
import com.example.psami_projekt.R;
import com.example.psami_projekt.ViewModel.ProductsViewModel;

import java.util.ArrayList;

public class BottomKcalFragment extends Fragment {

    private ProductsViewModel productsViewModel;
    private TextView txtKcal, txtProtein, txtFats, txtCarbs;
    private TextView txtMaxKcal, txtMaxProtein, txtMaxFats, txtMaxCarbs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_kcal, container, false);

        initTxtFields(view);

        calculateFieldsInDay();

        return view;
    }

    private void calculateFieldsInDay() {
        productsViewModel = new ProductsViewModel(getContext());
        ArrayList<ProductInMeal> products = productsViewModel.getProductsFromDay(Utils.getDate());
        Integer kcal = 0;
        Double protein = 0.0, fats = 0.0, carbs = 0.0;
        for (ProductInMeal product : products) {
            kcal += product.getKcal();
            protein += product.getProtein();
            fats += product.getFat();
            carbs += product.getCarbs();
        }
        txtKcal.setText(String.valueOf(kcal));
        txtProtein.setText(String.format("%.01f", protein));
        txtFats.setText(String.format("%.01f", fats));
        txtCarbs.setText(String.format("%.01f", carbs));
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