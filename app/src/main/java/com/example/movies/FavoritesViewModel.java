package com.example.movies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {

    private final MoviesDao moviesDao;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);

        this.moviesDao = MoviesDatabase.getInstance(getApplication()).moviesDao();
    }

    public LiveData<List<MoviePreview>> getFavorites() {
        return this.moviesDao.getAllFavoriteMovies();
    }
}
