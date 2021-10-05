package com.example.newsapp;

import java.util.ArrayList;

public class NewsModel {

//    url for all news
//    https://newsapi.org/v2/top-headlines?country=us&sortBy=publishedAt&apiKey=2e91dc551a3e4cf7803bde83b913d258

//    url for category
//    https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=2e91dc551a3e4cf7803bde83b913d258


    private int totalResults;
    private String status;
    private ArrayList<Articles> articles;

    public NewsModel(int totalResults, String status, ArrayList<Articles> articles) {
        this.totalResults = totalResults;
        this.status = status;
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }
}
