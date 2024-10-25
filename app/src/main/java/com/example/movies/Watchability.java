package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Watchability {

    @SerializedName("items")
    private List<Cinema> cinemas;

    public List<Cinema> getCinemas() {
        return this.cinemas;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "cinemas=" + this.cinemas +
                '}';
    }
}
