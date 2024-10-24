package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Videos {

    @SerializedName("trailers")
    private List<Trailer> trailers;

    public List<Trailer> getTrailers() {
        return this.trailers;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "trailers=" + trailers +
                '}';
    }
}
