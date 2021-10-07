package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Categories> categoriesArrayList;
    private Context context;
    private CategoryClickInterface categoryClickInterface;


    public CategoryRecyclerViewAdapter(ArrayList<Categories> categoriesArrayList, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoriesArrayList = categoriesArrayList;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_recyclerview_item, parent, false);

        return new CategoryRecyclerViewAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Categories categories = categoriesArrayList.get(position);
        holder.categoryTV.setText(categories.getCategory());

        Picasso.get().load(categories.getCategoryImageUrl()).into(holder.categoryIV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryClickInterface.onCategoryClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoriesArrayList.size();
    }


    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView categoryTV;
        private ImageView categoryIV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTV = itemView.findViewById(R.id.categoryTV);
            categoryIV = itemView.findViewById(R.id.categoryIV);

        }
    }
}


