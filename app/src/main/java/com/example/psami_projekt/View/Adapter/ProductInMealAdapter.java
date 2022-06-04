package com.example.psami_projekt.View.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psami_projekt.Model.ProductInMeal;
import com.example.psami_projekt.Model.Utils;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.ProductActivity;
import com.example.psami_projekt.ViewModel.ProductsViewModel;

import java.util.ArrayList;

public class ProductInMealAdapter extends RecyclerView.Adapter<ProductInMealAdapter.ViewHolder> {

    private ArrayList<ProductInMeal> products = new ArrayList<>();
    private final Context context;

    public interface OnProductRecyclerListener {
        void deleteProduct(int position);
    }
    private final OnProductRecyclerListener onProductRecyclerListener;

    public ProductInMealAdapter(Context context, OnProductRecyclerListener onProductRecyclerListener) {
        this.context = context;
        this.onProductRecyclerListener = onProductRecyclerListener;
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
        String protein = String.format("%.01f", products.get(position).getProtein());
        String fat = String.format("%.01f", products.get(position).getFat());
        String carbs = String.format("%.01f", products.get(position).getCarbs());
        holder.txtProtein.setText(protein);
        holder.txtFat.setText(fat);
        holder.txtCarbs.setText(carbs);
        holder.txtGrams.setText(String.valueOf(products.get(position).getGrams()));

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra(ProductActivity.PRODUCT_ID_KEY, products.get(holder.getAbsoluteAdapterPosition()).getId());
                intent.putExtra(ProductActivity.PRODUCT_IN_MEAL_ID_KEY, products.get(holder.getAbsoluteAdapterPosition()).getIdInMealDB());
                context.startActivity(intent);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you wan to delete this product?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onProductRecyclerListener.deleteProduct(products.get(holder.getAbsoluteAdapterPosition()).getIdInMealDB());
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // nothing
                    }
                });
                builder.create().show();
            }
        });
    }

    public void setProducts(ArrayList<ProductInMeal> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtProductName, txtGrams, txtKcal, txtProtein, txtFat, txtCarbs;
        private final ImageButton btnDelete;
        private final ConstraintLayout parent;

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
