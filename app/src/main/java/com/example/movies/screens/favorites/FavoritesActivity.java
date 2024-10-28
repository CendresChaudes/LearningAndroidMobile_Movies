package com.example.movies.screens.favorites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.common.MoviePreview;
import com.example.movies.common.MoviesAdapter;
import com.example.movies.screens.moviedetails.MovieDetailsActivity;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private FavoritesViewModel viewModel;

    private RecyclerView recyclerViewMovies;
    private MoviesAdapter moviesAdapter;

    @NonNull
    public static Intent createIntent(Context context) {
        return new Intent(context, FavoritesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initActivity();
    }

    private void launchMovieDetailsScreen(MoviePreview movie) {
        Intent intent = MovieDetailsActivity.createIntent(this, movie);
        startActivity(intent);
    }

    private void initActivity() {
        this.initViews();
        this.initRecyclerViewMovies();
        this.initViewModel();

        this.setGetMoviesObserver();
        this.setOnMovieItemClickListener();
    }

    private void initViews() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
    }

    private void initViewModel() {
        this.viewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
    }

    private void initRecyclerViewMovies() {
        this.moviesAdapter = new MoviesAdapter();
        this.recyclerViewMovies.setAdapter(this.moviesAdapter);
    }

    private void setOnMovieItemClickListener() {
        this.moviesAdapter.setOnMovieItemClickListener(
                new MoviesAdapter.OnMovieItemClickListener() {
                    @Override
                    public void onClick(MoviePreview movie) {
                        launchMovieDetailsScreen(movie);
                    }
                });
    }

    private void setGetMoviesObserver() {
        this.viewModel.getFavorites().observe(
                this,
                new Observer<List<MoviePreview>>() {
                    @Override
                    public void onChanged(List<MoviePreview> movies) {
                        moviesAdapter.setMovies(movies);
                    }
                });
    }
}