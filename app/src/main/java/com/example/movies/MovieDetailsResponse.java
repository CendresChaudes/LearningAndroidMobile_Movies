package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class MovieDetailsResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("year")
    private int year;

    @SerializedName("poster")
    private Poster poster;

    @SerializedName("watchability")
    private Watchability watchability;

    public MovieDetailsResponse(
            int id,
            String name,
            String description,
            int year,
            Poster poster,
            Watchability watchability
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.poster = poster;
        this.watchability = watchability;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getYear() {
        return this.year;
    }

    public Poster getPoster() {
        return this.poster;
    }

    public Watchability getWatchability() {
        return this.watchability;
    }

    @Override
    public String toString() {
        return "MovieDetailsResponse{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", description='" + this.description + '\'' +
                ", year=" + this.year +
                ", poster=" + this.poster +
                ", watchability=" + this.watchability +
                '}';
    }
}
