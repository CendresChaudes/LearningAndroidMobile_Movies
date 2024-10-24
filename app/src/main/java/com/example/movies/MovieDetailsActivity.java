package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String MOVIE_INTENT_KEY = "movie";

    private MovieDetailsViewModel viewModel;

    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;
    private ProgressBar progressBarMovieDetailsLoading;

    @NonNull
    public static Intent createIntent(Context context, MoviePreview movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(MOVIE_INTENT_KEY, movie);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        this.initActivity();
    }

    private void initActivity() {
        this.initViews();
        this.initViewModel();
        this.loadMovieDetails();
        this.setIsMovieDetailsLoadingObserver();
        this.setMovieDetailsObserver();
    }

    private void initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        progressBarMovieDetailsLoading = findViewById(R.id.progressBarMovieDetailsLoading);
    }

    private void initViewModel() {
        this.viewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
    }

    private void loadMovieDetails() {
        MoviePreview movie = (MoviePreview) getIntent().getSerializableExtra(MOVIE_INTENT_KEY);
        this.viewModel.loadMovieDetails(movie.getId());
    }

    private void setIsMovieDetailsLoadingObserver() {
        this.viewModel.getIsMovieDetailsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    progressBarMovieDetailsLoading.setVisibility(View.VISIBLE);
                } else {
                    progressBarMovieDetailsLoading.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setMovieDetailsObserver() {
        this.viewModel.getMovieDetails().observe(this, new Observer<MovieDetailsResponse>() {
            @Override
            public void onChanged(MovieDetailsResponse movieDetailsResponse) {
                Glide
                        .with(MovieDetailsActivity.this)
                        .load(movieDetailsResponse.getPoster().getUrl())
                        .into(imageViewPoster);

                textViewTitle.setText(movieDetailsResponse.getName());
                textViewYear.setText(String.valueOf(movieDetailsResponse.getYear()));
                textViewDescription.setText(movieDetailsResponse.getDescription());
            }
        });
    }
}