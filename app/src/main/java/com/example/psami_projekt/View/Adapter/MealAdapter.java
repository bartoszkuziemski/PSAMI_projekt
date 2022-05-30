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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psami_projekt.Model.Meal;
import com.example.psami_projekt.Model.ProductInMeal;
import com.example.psami_projekt.Model.Utils;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.SearchActivity;
import com.example.psami_projekt.ViewModel.ProductsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    public interface OnMealRecyclerListener {
        void deleteProduct(int productPosition, int mealPosition);
    }

    private OnMealRecyclerListener onMealRecyclerListener;

    private final ProductsViewModel productsViewModel;
    private final Context context;
    private ArrayList<ProductInMeal> products;
    private final ArrayList<Meal> meals;
    private ProductInMealAdapter productInMealAdapter;

    public MealAdapter(Context context, ArrayList<Meal> meals) {
        this.context = context;
        this.productsViewModel = new ProductsViewModel(context);
        this.meals = meals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_meal_list, parent, false);
        return new MealAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        initProductInMealRecView(holder);

        calculateFieldsInMeal(holder);

        setTxtFields(holder, position);


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
    }

    private void setTxtFields(@NonNull ViewHolder holder, int position) {
        holder.txtMealName.setText(meals.get(position).getName());
        holder.txtMealKcal.setText(String.valueOf(meals.get(position).getKcal()));
        holder.txtMealProtein.setText(String.format("%.01f", meals.get(position).getProteins()));
        holder.txtMealFats.setText(String.format("%.01f", meals.get(position).getFats()));
        holder.txtMealCarbs.setText(String.format("%.01f", meals.get(position).getCarbs()));
    }

    /**
     * Calculate kcal, protein, fats, carbs in meal from all products
     * @param holder h
     */
    private void calculateFieldsInMeal(ViewHolder holder) {
        Meal meal = meals.get(holder.getAbsoluteAdapterPosition());
        Integer kcal = 0;
        Double protein = 0.0, fats = 0.0, carbs = 0.0;
        for (ProductInMeal product : products) {
            kcal += product.getKcal();
            protein += product.getProtein();
            fats += product.getFat();
            carbs += product.getCarbs();
        }
        meal.setKcal(kcal);
        meal.setProteins(protein);
        meal.setFats(fats);
        meal.setCarbs(carbs);
    }

    private void initProductInMealRecView(@NonNull ViewHolder holder) {
        productInMealAdapter = new ProductInMealAdapter(context, holder);
        products = productsViewModel.getProductsFromMeal(Utils.getDate(), meals.get(holder.getAbsoluteAdapterPosition()).getName());
        productInMealAdapter.setProducts(products);
        holder.productRecView.setAdapter(productInMealAdapter);
        holder.productRecView.setLayoutManager(new LinearLayoutManager(context));
    }

//    public void setMeals(ArrayList<Meal> meals) {
//        this.meals = meals;
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ProductInMealAdapter.OnProductRecyclerListener{

        private TextView txtMealName, txtMealKcal, txtMealProtein, txtMealFats, txtMealCarbs;
        private FloatingActionButton fabAddMeal;
        private ConstraintLayout parent;
        private RecyclerView productRecView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMealName = itemView.findViewById(R.id.txtMealName);
            txtMealKcal = itemView.findViewById(R.id.txtMealKcal);
            txtMealProtein = itemView.findViewById(R.id.txtMealProtein);
            txtMealFats = itemView.findViewById(R.id.txtMealFat);
            txtMealCarbs = itemView.findViewById(R.id.txtMealCarbs);

            fabAddMeal = itemView.findViewById(R.id.fabAddProduct);
            parent = itemView.findViewById(R.id.mealListLayoutParent);
            productRecView = itemView.findViewById(R.id.productsInMealRecView);
        }

        @Override
        public void deleteProduct(int productId) {
            onMealRecyclerListener.deleteProduct(productId, getAbsoluteAdapterPosition());
        }
    }

    public void setOnMealRecyclerListener(OnMealRecyclerListener onMealRecyclerListener) {
        this.onMealRecyclerListener = onMealRecyclerListener;
    }

    public ProductInMealAdapter getProductInMealAdapter() {
        return productInMealAdapter;
    }
}
