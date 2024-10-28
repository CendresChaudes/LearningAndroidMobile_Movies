package com.example.movies.screens.favorites;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movies.common.MoviePreview;
import com.example.movies.database.MoviesDao;
import com.example.movies.database.MoviesDatabase;

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
