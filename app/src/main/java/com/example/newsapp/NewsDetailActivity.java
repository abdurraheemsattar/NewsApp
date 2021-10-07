package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NewsDetailActivity extends AppCompatActivity {

    String title, content, description, image, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        description = getIntent().getStringExtra("description");
        image = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");


    }



}