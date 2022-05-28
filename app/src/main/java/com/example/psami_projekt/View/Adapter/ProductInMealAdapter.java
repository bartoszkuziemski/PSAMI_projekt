package com.example.psami_projekt.View.Adapter;

import android.content.Context;
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
import com.example.psami_projekt.ViewModel.ProductsViewModel;

import java.util.ArrayList;

public class ProductInMealAdapter extends RecyclerView.Adapter<ProductInMealAdapter.ViewHolder> {

    private ArrayList<ProductInMeal> products = new ArrayList<>();
    private Context context;
    private ProductsViewModel productsViewModel;

    public interface OnProductRecyclerListener {
        void deleteProduct(int position);
    }
    private OnProductRecyclerListener onProductRecyclerListener;

    public ProductInMealAdapter(Context context, ProductsViewModel productsViewModel, OnProductRecyclerListener onProductRecyclerListener) {
        this.context = context;
        this.productsViewModel = productsViewModel;
        this.onProductRecyclerListener = onProductRecyclerListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_in_meal, parent, false);
        return new ViewHolder(view, context, onProductRecyclerListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtProductName.setText(products.get(position).getName());
        holder.txtKcal.setText(String.valueOf(products.get(position).getKcal()));
        String protein = String.format("%.02f", products.get(position).getProtein());
        String fat = String.format("%.02f", products.get(position).getFat());
        String carbs = String.format("%.02f", products.get(position).getCarbs());
        holder.txtProtein.setText(protein);
        holder.txtFat.setText(fat);
        holder.txtCarbs.setText(carbs);
        holder.txtGrams.setText(String.valueOf(products.get(position).getGrams()));

//        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (productsViewModel.deleteProductFromMeal(products.get(holder.getAbsoluteAdapterPosition()), Utils.getDate(), products.get(holder.getAbsoluteAdapterPosition()).getName())) {
//                    Toast.makeText(context, "Product deleted successfully", Toast.LENGTH_SHORT).show();
//                    notifyItemRemoved(holder.getAbsoluteAdapterPosition());
//                } else {
//                    Toast.makeText(context, "Cannot delete product, try again", Toast.LENGTH_SHORT).show();
//                }
//
////                try {
////                    removeProductFromMeal = (RemoveProductFromMeal) context;
////                    removeProductFromMeal.onRemoveProduct(products.get(holder.getAbsoluteAdapterPosition()));
////                } catch (ClassCastException e) {
////                    e.printStackTrace();
////                }
//            }
//        });

    }

    public void setProducts(ArrayList<ProductInMeal> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private OnProductRecyclerListener onProductRecyclerListener;

        private TextView txtProductName, txtGrams, txtKcal, txtProtein, txtFat, txtCarbs;
        private ImageButton btnDelete;
        private ConstraintLayout parent;

        public ViewHolder(@NonNull View itemView, Context context, OnProductRecyclerListener onProductRecyclerListener) {
            super(itemView);

            this.context = context;
            this.onProductRecyclerListener = onProductRecyclerListener;

            txtProductName = itemView.findViewById(R.id.txtProductInMealName);
            txtGrams = itemView.findViewById(R.id.txtProductGramsInMeal);
            txtKcal = itemView.findViewById(R.id.txtProductInMealKcal);
            txtProtein = itemView.findViewById(R.id.txtProductInMealProtein);
            txtFat = itemView.findViewById(R.id.txtProductInMealFat);
            txtCarbs = itemView.findViewById(R.id.txtProductInMealCarbs);
            btnDelete = itemView.findViewById(R.id.btnDeleteFromMeal);
            parent = itemView.findViewById(R.id.productsInMealParent);

            loadListeners(itemView);
        }

        private void loadListeners(View itemView) {
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onProductRecyclerListener.deleteProduct(products.get(getAbsoluteAdapterPosition()).getId());
                }
            });
        }
    }

}
