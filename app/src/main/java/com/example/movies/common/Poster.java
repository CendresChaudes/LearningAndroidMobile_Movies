package com.example.movies.common;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Poster implements Serializable {

    @SerializedName("url")
    private final String url;

    public Poster(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    @Override
    public String toString() {
        return "Poster{" +
                "url='" + this.url + '\'' +
                '}';
    }
}
