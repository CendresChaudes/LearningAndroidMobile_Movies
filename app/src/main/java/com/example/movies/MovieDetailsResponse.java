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

    @SerializedName("videos")
    private Videos videos;

    public MovieDetailsResponse(
            int id,
            String name,
            String description,
            int year,
            Poster poster,
            Videos videos
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.poster = poster;
        this.videos = videos;
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

    public Videos getVideos() {
        return this.videos;
    }

    @Override
    public String toString() {
        return "MovieDetailsResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", poster=" + poster +
                ", videos=" + videos +
                '}';
    }
}
