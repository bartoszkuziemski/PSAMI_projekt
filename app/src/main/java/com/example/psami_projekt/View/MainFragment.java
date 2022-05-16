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

import com.example.psami_projekt.Model.DatabaseHelper;
import com.example.psami_projekt.Model.Meal;
import com.example.psami_projekt.Model.MealDatabaseHelper;
import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.Adapter.MealAdapter;
import com.example.psami_projekt.View.Adapter.ProductInMealAdapter;
import com.example.psami_projekt.ViewModel.ProductsViewModel;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainFragment extends Fragment {

    private RecyclerView mealRecView;
    private MealAdapter mealAdapter;
    private String dayId;

    private MealDatabaseHelper databaseHelper;

    // tests
    private ProductInMealAdapter adapter;
    private RecyclerView recyclerView;
    private ProductsViewModel productsViewModel = new ProductsViewModel(getContext());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);

        if (getArguments() != null) {
             dayId = getArguments().getString(CalendarActivity.DAY_ID_KEY);
             Toast.makeText(getActivity(), dayId, Toast.LENGTH_SHORT).show();
        }

        mealRecView = view.findViewById(R.id.mealRecView);
        mealAdapter = new MealAdapter(getContext());
        mealRecView.setAdapter(mealAdapter);
        mealRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        MealDatabaseHelper.initMeals();
        ArrayList<Meal> meals = MealDatabaseHelper.getMeals();
        if (meals != null) {
            mealAdapter.setMeals(meals);
        }

        // tests
//        recyclerView = view.findViewById(R.id.mealRecView);
//        adapter = new ProductInMealAdapter(getContext());
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        ArrayList<Product> products = productsViewModel.getStaringProducts();
//        adapter.setProducts(products);



        return view;
    }

    private void initViews(View view) {

        // tests
    }
}
