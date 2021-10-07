package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    String title, content, description, imageURL, url;

    private TextView titleDetailTV, subDescTV, contentTV;
    private ImageView newsDetailIV;
    private Button readNewsBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        title = getIntent().getStringExtra("title");
//        content = getIntent().getStringExtra("content");
        content = getIntent().getStringExtra("url");
        description = getIntent().getStringExtra("description");
        imageURL = getIntent().getStringExtra("image");
//        url = getIntent().getStringExtra("url");
        url = getIntent().getStringExtra("content");


        titleDetailTV = findViewById(R.id.titleDetailTV);
        subDescTV = findViewById(R.id.subDescTV);
        contentTV = findViewById(R.id.contentTV);
        newsDetailIV = findViewById(R.id.newsDetailIV);
        readNewsBtn = findViewById(R.id.readNewsBtn);

        titleDetailTV.setText(title);
        subDescTV.setText(description);
        contentTV.setText(content);

        Picasso.get().load(imageURL).into(newsDetailIV);

        readNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });




    }



}