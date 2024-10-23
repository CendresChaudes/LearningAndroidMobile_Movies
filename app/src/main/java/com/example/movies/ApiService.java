package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie?rating.kp=7-10&sortField=votes.kp&sortType=-1&limit=10")
    @Headers("X-API-KEY: TV54K1C-9Q4M9V7-KGMEHNV-WBVJQJ9")
    Single<MovieResponse> getMovies(@Query("page") int page);
}
