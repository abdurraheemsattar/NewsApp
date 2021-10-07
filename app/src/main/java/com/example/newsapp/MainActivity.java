package com.example.newsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        getNews("All");
        newsRecyclerViewAdapter.notifyDataSetChanged();

    }


    private void getCategories() {

        categoriesArrayList.add(new Categories("All", "https://images.unsplash.com/photo-1584714268709-c3dd9c92b378?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8YWxsJTIwbmV3c3xlbnwwfDB8MHx3aGl0ZXw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));
        categoriesArrayList.add(new Categories("Business", "https://images.unsplash.com/photo-1591696205602-2f950c417cb9?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTR8fGJ1c2luZXNzfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));

        categoriesArrayList.add(new Categories("Entertainment", "https://images.unsplash.com/photo-1536331014686-7c8210a64010?ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDd8fGVudGVydGFpbm1lbnR8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));
        categoriesArrayList.add(new Categories("General", "https://images.unsplash.com/photo-1586339393494-d109a7b9959c?ixid=MnwxMjA3fDB8MHxzZWFyY2h8ODZ8fG5ld3N8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));

        categoriesArrayList.add(new Categories("Health", "https://images.unsplash.com/photo-1505751172876-fa1923c5c528?ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8aGVhbHRofGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));
        categoriesArrayList.add(new Categories("Science", "https://images.unsplash.com/photo-1532094349884-543bc11b234d?ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8c2NpZW5jZXN8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));

        categoriesArrayList.add(new Categories("Sports", "https://images.unsplash.com/photo-1545469213-e118da4ad7d9?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjJ8fGZpdG5lc3N8ZW58MHwwfDB8d2hpdGV8&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));
        categoriesArrayList.add(new Categories("Technology", "https://images.unsplash.com/photo-1601132359864-c974e79890ac?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTUxfHxzY2llbmNlfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));

        categoryRecyclerViewAdapter.notifyDataSetChanged();

    }

    private void getNews(String category) {
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();

        // "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=2e91dc551a3e4cf7803bde83b913d258";

        String apiKey = "2e91dc551a3e4cf7803bde83b913d258";
        String country = "us";


        String categoryUrl = "https://newsapi.org/v2/top-headlines?country=" + country + "&category=" + category + "&apiKey=" + apiKey;
        String url = "https://newsapi.org/v2/top-headlines?country=" + country + "&apiKey=" + apiKey;
        String BASE_URL = "https://newsapi.org/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModel> call;

        if (category.equals("All")) {
            call = retrofitAPI.getAllNews(url);
        } else {
            call = retrofitAPI.getNewsByCategory(categoryUrl);
        }

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModel.getArticles();
                for (int i = 0; i < articles.size(); i++) {
                    articlesArrayList.add(new Articles(
                            articles.get(i).getTitle(),
                            articles.get(i).getContent(),
                            articles.get(i).getDescription(),
                            articles.get(i).getUrlToImage(),
                            articles.get(i).getUrl()));

                }
                newsRecyclerViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to get news", Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onCategoryClick(int position) {

        String category = categoriesArrayList.get(position).getCategory();
        getNews(category);
    }


}