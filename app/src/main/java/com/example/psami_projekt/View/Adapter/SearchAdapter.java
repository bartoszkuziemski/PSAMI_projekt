package com.example.psami_projekt.View.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psami_projekt.Model.Product;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.ProductActivity;
import com.example.psami_projekt.ViewModel.ProductsViewModel;

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
        holder.txtProductKcal.setText(String.valueOf(searchedProducts.get(position).getKcal()));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// TODO: 27.04.2022 go to product activity
                //Toast.makeText(context, "product clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra(ProductActivity.PRODUCT_ID_KEY, searchedProducts.get(holder.getAbsoluteAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });
//        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage("Are you sure you want to delete " + searchedProducts.get(position).getName() + "?");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        // TODO: 03.05.2022 delete item from db
//                        //notifyDataSetChanged();
//                        Toast.makeText(context, "item deleted", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        // nothing
//                    }
//                });
//                return true;
//            }
//        });
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
            this.txtProductKcal = itemView.findViewById(R.id.txtProductKcal);
            this.parent = itemView.findViewById(R.id.searchLayoutParent);
        }
    }
}
