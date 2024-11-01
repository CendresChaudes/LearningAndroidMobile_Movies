package com.example.movies.common;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("author")
    private final String author;

    @SerializedName("review")
    private final String text;

    @SerializedName("type")
    private final ReviewType type;

    public Review(String author, String text, ReviewType type) {
        this.author = author;
        this.text = text;
        this.type = type;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getText() {
        return this.text;
    }

    public ReviewType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Review{" +
                "author='" + this.author + '\'' +
                ", text='" + this.text + '\'' +
                ", type=" + this.type +
                '}';
    }
}
