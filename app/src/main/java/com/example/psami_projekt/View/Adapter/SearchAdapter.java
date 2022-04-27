package com.example.psami_projekt.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<Product> searchedProducts = new ArrayList<>();
    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtProductName.setText(searchedProducts.get(position).getName());
        holder.txtProductDesc.setText(searchedProducts.get(position).getDescription());
        holder.txtProductKcal.setText(searchedProducts.get(position).getKcal());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// TODO: 27.04.2022 go to product activity
            }
        });
    }

    public void setSearchedProducts(ArrayList<Product> searchedProducts) {
        this.searchedProducts = searchedProducts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return searchedProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtProductName, txtProductDesc, txtProductKcal;
        private ConstraintLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtProductName = itemView.findViewById(R.id.txtProductName);
            this.txtProductDesc = itemView.findViewById(R.id.txtProductDesc);
            this.txtProductKcal = itemView.findViewById(R.id.txtMealKcal);
            this.parent = itemView.findViewById(R.id.searchLayoutParent);
        }
    }
}