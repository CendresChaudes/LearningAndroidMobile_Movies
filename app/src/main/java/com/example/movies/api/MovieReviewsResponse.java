package com.example.movies.api;

import com.example.movies.common.Review;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieReviewsResponse {

    @SerializedName("docs")
    private final List<Review> reviews;

    public MovieReviewsResponse(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }

    @Override
    public String toString() {
        return "MovieReviewsResponse{" +
                "reviews=" + this.reviews +
                '}';
    }
}
