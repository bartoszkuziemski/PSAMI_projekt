package com.example.psami_projekt.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psami_projekt.Model.Meal;
import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.Model.ProductInMeal;
import com.example.psami_projekt.Model.Utils;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.MainActivity;
import com.example.psami_projekt.View.SearchActivity;
import com.example.psami_projekt.ViewModel.ProductsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> implements ProductInMealAdapter.RemoveProductFromMeal {

    private ArrayList<Meal> meals = new ArrayList<>();
    private Context context;
    private ProductInMealAdapter productInMealAdapter;
    private ProductsViewModel productsViewModel;

    public MealAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        productsViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ProductsViewModel.class);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_meal_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMealName.setText(meals.get(position).getName());
        holder.txtMealKcal.setText(String.valueOf(meals.get(position).getKcal()));
        holder.fabAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.setMeal(meals.get(holder.getAbsoluteAdapterPosition()).getName());
                Intent intent = new Intent(context, SearchActivity.class);
                context.startActivity(intent);
            }
        });
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// TODO: 25.04.2022 onClickListener after click on meal - show products in meal
                Toast.makeText(context, "Meal clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // tests
//        switch (meals.get(holder.getAbsoluteAdapterPosition()).getName()) {
//            case "Breakfast":
//                ArrayList<Product> products = productsViewModel.getProductsFromMeal(Utils.getDate(), );
//                productInMealAdapter.setProducts(products);
//        }



        //productsViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ProductsViewModel.class);
        productsViewModel.init(context);
        productInMealAdapter = new ProductInMealAdapter(context, productsViewModel);
        productsViewModel.getProductsFromMeal(Utils.getDate(), meals.get(holder.getAbsoluteAdapterPosition()).getName()).observe( (LifecycleOwner) context, new Observer<ArrayList<ProductInMeal>>() {
            @Override
            public void onChanged(ArrayList<ProductInMeal> productInMeals) {
                productInMealAdapter.notifyDataSetChanged();
            }
        });


        productInMealAdapter.setProducts(productsViewModel.getProductsFromMeal(Utils.getDate(), meals.get(holder.getAbsoluteAdapterPosition()).getName()).getValue());
        holder.productRecView.setAdapter(productInMealAdapter);
        holder.productRecView.setLayoutManager(new LinearLayoutManager(context));

        //ArrayList<ProductInMeal> products = productsViewModel.getProductsFromMeal(Utils.getDate(), meals.get(holder.getAbsoluteAdapterPosition()).getName());
        //productInMealAdapter.setProducts(products);

    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @Override
    public void onRemoveProduct(ProductInMeal product) {
        if (productsViewModel.deleteProductFromMeal(product, Utils.getDate(), "Breakfast")) {
            Toast.makeText(context, "Product deleted successfully", Toast.LENGTH_SHORT).show();
            //ArrayList<ProductInMeal> products = productsViewModel.getProductsFromMeal(Utils.getDate(), "Dinner");
            //productInMealAdapter.setProducts(products);
        }
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtMealName, txtMealKcal;
        private FloatingActionButton fabAddMeal;
        private ConstraintLayout parent;
        private RecyclerView productRecView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMealName = itemView.findViewById(R.id.txtMealName);
            txtMealKcal = itemView.findViewById(R.id.txtMealKcal);
            fabAddMeal = itemView.findViewById(R.id.fabAddProduct);
            parent = itemView.findViewById(R.id.mealListLayoutParent);
            productRecView = itemView.findViewById(R.id.productsInMealRecView);
        }
    }
}
