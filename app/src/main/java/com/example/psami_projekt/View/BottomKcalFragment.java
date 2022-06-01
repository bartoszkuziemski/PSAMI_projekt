package com.example.psami_projekt.View;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.psami_projekt.Model.ProductInMeal;
import com.example.psami_projekt.Model.Utils;
import com.example.psami_projekt.R;
import com.example.psami_projekt.ViewModel.ProductsViewModel;

import java.util.ArrayList;

public class BottomKcalFragment extends Fragment implements MainFragment.BottomFragmentListener {

    private ProductsViewModel productsViewModel;
    private TextView txtKcal, txtProtein, txtFats, txtCarbs;
    private TextView txtMaxKcal, txtMaxProtein, txtMaxFats, txtMaxCarbs;
    private final Utils utils = Utils.getInstance(getContext());
    private ProgressBar progressBarKcal, progressBarProteins, progressBarFats, progressBarCarbs;
    private Integer maxKcal, maxProteins, maxFats, maxCarbs;
    private Integer kcal = 0;
    private Double protein = 0.0, fats = 0.0, carbs = 0.0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_kcal, container, false);

        initTxtFields(view);
        calculateFieldsInDay();
        setProgressBars();

        return view;
    }

    private void calculateFieldsInDay() {
        productsViewModel = new ProductsViewModel(getContext());
        ArrayList<ProductInMeal> products = productsViewModel.getProductsFromDay(Utils.getDate());

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

    private void setProgressBars() {
        maxKcal = utils.getMaxKcal();
        maxProteins = utils.getMaxProteins();
        maxFats = utils.getMaxFats();
        maxCarbs = utils.getMaxCarbs();

        setTxtMaxAndProgressBarMax(maxKcal, kcal, txtMaxKcal, progressBarKcal);
        setTxtMaxAndProgressBarMax(maxProteins, protein.intValue(), txtMaxProtein, progressBarProteins);
        setTxtMaxAndProgressBarMax(maxFats, fats.intValue(), txtMaxFats, progressBarFats);
        setTxtMaxAndProgressBarMax(maxCarbs, carbs.intValue(), txtMaxCarbs, progressBarCarbs);
    }

    private void setTxtMaxAndProgressBarMax(Integer maxValue, Integer value, TextView txtMaxValue, ProgressBar progressBar) {
        txtMaxValue.setText(String.valueOf(maxValue));
        progressBar.setMin(0);
        progressBar.setMax(maxValue);
        setProgressBarValue(maxValue, value, progressBar);
    }

    /**
     * Set progressbar value and change color accordingly to max value
     * @param maxValue max value of field
     * @param value current value
     * @param progressBar p
     */
    private void setProgressBarValue(Integer maxValue, Integer value, ProgressBar progressBar) {
        progressBar.setProgress(value);
        if (value > maxValue) {
            progressBar.setProgressTintList(ContextCompat.getColorStateList(getContext(), R.color.red));
        } else {
            progressBar.setProgressTintList(ContextCompat.getColorStateList(getContext(), R.color.blue));
        }
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
        progressBarKcal = view.findViewById(R.id.progressBarKcal);
        progressBarProteins = view.findViewById(R.id.progressBarProtein);
        progressBarFats = view.findViewById(R.id.progressBarFats);
        progressBarCarbs = view.findViewById(R.id.progressBarCarbs);
    }

    /**
     * Updating fields after deleting product from meal
     * Reset fields, calculate new from database, set only progressbar values again
     */
    @Override
    public void update() {
        kcal = 0;
        protein = 0.0;
        fats = 0.0;
        carbs = 0.0;
        calculateFieldsInDay();
        setProgressBarValue(maxKcal, kcal, progressBarKcal);
        setProgressBarValue(maxProteins, protein.intValue(), progressBarProteins);
        setProgressBarValue(maxFats, fats.intValue(), progressBarFats);
        setProgressBarValue(maxCarbs, carbs.intValue(), progressBarCarbs);
    }
}