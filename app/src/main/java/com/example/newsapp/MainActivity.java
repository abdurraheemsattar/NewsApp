package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoryRecyclerViewAdapter.CategoryClickInterface {

    private RecyclerView newsRecyclerView;
    private RecyclerView categoryRecyclerView;
    private ProgressBar loadingPB;

    private ArrayList<Articles> articlesArrayList;
    private ArrayList<Categories> categoriesArrayList;

    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private CategoryRecyclerViewAdapter categoryRecyclerViewAdapter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsRecyclerView = findViewById(R.id.newsRV);
        categoryRecyclerView = findViewById(R.id.categoriesRV);
        loadingPB = findViewById(R.id.loadingPB);

        articlesArrayList = new ArrayList<>();
        categoriesArrayList = new ArrayList<>();

        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(articlesArrayList, this);
        categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(categoriesArrayList, this, this::onCategoryClick);

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.setAdapter(newsRecyclerViewAdapter);

//        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryRecyclerView.setAdapter(categoryRecyclerViewAdapter);

        getCategories();

    }


    private void getCategories(){

        categoriesArrayList.add(new Categories("All", ""));
        categoriesArrayList.add(new Categories("Business", ""));

        categoriesArrayList.add(new Categories("Entertainment", ""));
        categoriesArrayList.add(new Categories("General", ""));

        categoriesArrayList.add(new Categories("Health", ""));
        categoriesArrayList.add(new Categories("science", ""));

        categoriesArrayList.add(new Categories("Sports", ""));
        categoriesArrayList.add(new Categories("Technology", ""));

        categoryRecyclerViewAdapter.notifyDataSetChanged();

    }

    private void getNews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();

        // "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=2e91dc551a3e4cf7803bde83b913d258";

        String apiKey = "2e91dc551a3e4cf7803bde83b913d258";
        String country = "us";


        String categoryUrl = "https://newsapi.org/v2/top-headlines?country="+ country +"&category="+ category +"&apiKey=" + apiKey ;
        String url = "https://newsapi.org/v2/top-headlines?country="+ country +"&apiKey=" + apiKey ;

        String BASE_URL = "https://newsapi.org/";

        








    }



    @Override
    public void onCategoryClick(int position) {

    }






















}