package com.example.movies;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MoviesDao {

    @Query("SELECT * FROM favorites")
    LiveData<List<MoviePreview>> getAllFavoriteMovies();

    @Query("SELECT * FROM favorites WHERE id = :id")
    LiveData<MoviePreview> getFavoriteMovieById(int id);

    @Insert
    Completable createFavoriteMovie(MoviePreview moviePreview);

    @Query("DELETE FROM favorites WHERE id = :id")
    Completable deleteFavoriteMovie(int id);
}
