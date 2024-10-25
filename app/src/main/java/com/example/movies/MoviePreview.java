package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MoviePreview implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("poster")
    private Poster poster;

    @SerializedName("rating")
    private Rating rating;

    public MoviePreview(
            int id,
            Poster poster,
            Rating rating
    ) {
        this.id = id;
        this.poster = poster;
        this.rating = rating;
    }

    public int getId() {
        return this.id;
    }

    public Poster getPoster() {
        return this.poster;
    }

    public Rating getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        return "MoviePreview{" +
                "id=" + this.id +
                ", poster=" + this.poster +
                ", rating=" + this.rating +
                '}';
    }
}
