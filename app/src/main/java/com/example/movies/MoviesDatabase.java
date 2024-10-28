package com.example.movies;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MoviePreview.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {

    private static final String DB_NAME = "movies.db";

    private static MoviesDatabase instance;

    public static MoviesDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            application,
                            MoviesDatabase.class,
                            DB_NAME
                    )
                    .build();
        }

        return instance;
    }

    public abstract MoviesDao moviesDao();
}
