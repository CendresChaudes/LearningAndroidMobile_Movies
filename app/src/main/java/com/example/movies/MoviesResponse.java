package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("docs")
    private List<MoviePreview> movies;

    public MoviesResponse(List<MoviePreview> movies) {
        this.movies = movies;
    }

    public List<MoviePreview> getMovies() {
        return this.movies;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movies=" + this.movies +
                '}';
    }
}
