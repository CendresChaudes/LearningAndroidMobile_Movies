package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String API_KEY = "TV54K1C-9Q4M9V7-KGMEHNV-WBVJQJ9";

    @GET("movie?rating.kp=7-10&sortField=votes.kp&sortType=-1&limit=10")
    @Headers("X-API-KEY:" + API_KEY)
    Single<MoviesResponse> getMovies(@Query("page") int page);

    @GET("movie/{id}")
    @Headers("X-API-KEY:" + API_KEY)
    Single<MovieDetailsResponse> getMovieDetails(@Path("id") int id);

    @GET("review?page=1&limit=5")
    @Headers("X-API-KEY:" + API_KEY)
    Single<MovieReviewsResponse> getMovieReviews(@Query("movieId") int id);
}
