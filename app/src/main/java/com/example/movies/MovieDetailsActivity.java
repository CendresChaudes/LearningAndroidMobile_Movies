package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String MOVIE_INTENT_KEY = "movie";

    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;

    public static Intent createIntent(Context context, Movie movie) {
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
        this.fillMovieItem();
    }

    private void initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
    }

    private void fillMovieItem() {
        Movie movie = (Movie) getIntent().getSerializableExtra(MOVIE_INTENT_KEY);

        Glide
                .with(this)
                .load(movie.getPoster().getUrl())
                .into(this.imageViewPoster);

        this.textViewTitle.setText(movie.getName());
        this.textViewYear.setText(String.valueOf(movie.getYear()));
        this.textViewDescription.setText(movie.getDescription());
    }
}