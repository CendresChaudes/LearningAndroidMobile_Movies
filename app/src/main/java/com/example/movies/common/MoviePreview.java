package com.example.movies.common;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "favorites")
public class MoviePreview implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    private final int id;

    @Embedded
    @SerializedName("poster")
    private final Poster poster;

    @Embedded
    @SerializedName("rating")
    private final Rating rating;

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
