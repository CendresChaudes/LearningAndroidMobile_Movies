package com.example.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private RecyclerView recyclerViewMovies;
    private MoviesAdapter moviesAdapter;
    private ProgressBar progressBarMoviesLoading;

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
        this.initViewModel();
        this.initRecyclerViewMovies();
        this.setOnMovieItemClickListener();
        this.setOnReachEndListener();
        this.setGetMoviesObserver();
        this.setIsMoviesLoadingObserver();
    }

    private void initViews() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        progressBarMoviesLoading = findViewById(R.id.progressBarMoviesLoading);
    }

    private void initViewModel() {
        this.viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void initRecyclerViewMovies() {
        this.moviesAdapter = new MoviesAdapter();
        this.recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));
        this.recyclerViewMovies.setAdapter(moviesAdapter);
    }

    private void setOnMovieItemClickListener() {
        this.moviesAdapter.setOnMovieItemClickListener(new MoviesAdapter.OnMovieItemClickListener() {
            @Override
            public void onClick(MoviePreview movie) {
                launchMovieDetailsScreen(movie);
            }
        });
    }

    private void setOnReachEndListener() {
        this.moviesAdapter.setOnReachEndListener(new MoviesAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                viewModel.loadMovies();
            }
        });
    }

    private void setGetMoviesObserver() {
        this.viewModel.getMovies().observe(this, new Observer<List<MoviePreview>>() {
            @Override
            public void onChanged(List<MoviePreview> movies) {
                moviesAdapter.setMovies(movies);
            }
        });
    }

    private void setIsMoviesLoadingObserver() {
        viewModel.getIsMoviesLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    progressBarMoviesLoading.setVisibility(View.VISIBLE);
                } else {
                    progressBarMoviesLoading.setVisibility(View.GONE);
                }
            }
        });
    }
}