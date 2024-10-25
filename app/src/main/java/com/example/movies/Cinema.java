package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Cinema {

    @SerializedName("name")
    private String title;

    @SerializedName("url")
    private String url;

    public Cinema(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
