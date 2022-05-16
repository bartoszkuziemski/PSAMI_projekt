package com.example.psami_projekt.View.Adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.R;

import java.util.ArrayList;

public class ProductInMealAdapter extends RecyclerView.Adapter<ProductInMealAdapter.ViewHolder> {

    private ArrayList<Product> products = new ArrayList<>();
    private Context context;

    public ProductInMealAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_in_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtProductName.setText(products.get(position).getName());
        holder.txtKcal.setText(String.valueOf(products.get(position).getKcal()));
        holder.txtProtein.setText(String.valueOf(products.get(position).getProtein()));
        holder.txtFat.setText(String.valueOf(products.get(position).getFat()));
        holder.txtCarbs.setText(String.valueOf(products.get(position).getCarbs()));
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtProductName, txtGrams, txtKcal, txtProtein, txtFat, txtCarbs;
        private ImageButton btnDelete;
        private ConstraintLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txtProductInMealName);
            txtGrams = itemView.findViewById(R.id.txtProductGramsInMeal);
            txtKcal = itemView.findViewById(R.id.txtProductInMealKcal);
            txtProtein = itemView.findViewById(R.id.txtProductInMealProtein);
            txtFat = itemView.findViewById(R.id.txtProductInMealFat);
            txtCarbs = itemView.findViewById(R.id.txtProductInMealCarbs);
            btnDelete = itemView.findViewById(R.id.btnDeleteFromMeal);
            parent = itemView.findViewById(R.id.productsInMealParent);
        }
    }

}
