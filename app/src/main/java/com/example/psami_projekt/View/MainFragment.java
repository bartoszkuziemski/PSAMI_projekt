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
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.Adapter.MealAdapter;
import com.example.psami_projekt.ViewModel.ProductsViewModel;

import java.util.ArrayList;

public class MainFragment extends Fragment implements MealAdapter.OnMealRecyclerListener {

    private ProductsViewModel productsViewModel;
    private RecyclerView mealRecView;
    private MealAdapter mealAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initMealRecView(view);

        return view;
    }

    private void initMealRecView(View view) {
        Meal.initMeals();
        ArrayList<Meal> meals = Meal.getMeals();
        mealRecView = view.findViewById(R.id.mealRecView);
        mealAdapter = new MealAdapter(getContext(), meals);
        mealAdapter.setOnMealRecyclerListener(this);
        mealRecView.setAdapter(mealAdapter);
        mealRecView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    public void deleteProduct(int productId, int mealPosition) {
        productsViewModel = new ProductsViewModel(getContext());
        productsViewModel.deleteProductFromMeal(productId);
        mealAdapter.notifyDataSetChanged();
        mealAdapter.getProductInMealAdapter().notifyDataSetChanged();
    }
}
