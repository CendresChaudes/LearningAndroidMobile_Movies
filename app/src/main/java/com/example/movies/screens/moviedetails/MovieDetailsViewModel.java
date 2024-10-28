package com.example.movies.screens.moviedetails;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movies.api.ApiFactory;
import com.example.movies.api.ApiService;
import com.example.movies.api.MovieDetailsResponse;
import com.example.movies.api.MovieReviewsResponse;
import com.example.movies.common.MoviePreview;
import com.example.movies.common.Review;
import com.example.movies.database.MoviesDao;
import com.example.movies.database.MoviesDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailsViewModel extends AndroidViewModel {

    private static final String CLASS_NAME = "MovieDetailsViewModel";

    private final ApiService apiService;
    private final MoviesDao moviesDao;

    private final MutableLiveData<MovieDetailsResponse> movieDetails;
    private final MutableLiveData<List<Review>> movieReviews;
    private final MutableLiveData<Boolean> isMovieDetailsLoading;

    private final CompositeDisposable compositeDisposable;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);

        this.apiService = ApiFactory.apiService;
        this.moviesDao = MoviesDatabase.getInstance(getApplication()).moviesDao();
        this.movieDetails = new MutableLiveData<>();
        this.movieReviews = new MutableLiveData<>();
        this.isMovieDetailsLoading = new MutableLiveData<>(false);
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        this.compositeDisposable.dispose();
    }

    public void loadMovieDetails(int id) {
        Disposable disposable = this.apiService
                .getMovieDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        isMovieDetailsLoading.setValue(true);
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Throwable {
                        isMovieDetailsLoading.setValue(false);
                    }
                })
                .subscribe(new Consumer<MovieDetailsResponse>() {
                               @Override
                               public void accept(MovieDetailsResponse movieDetailsResponse) throws Throwable {
                                   movieDetails.setValue(movieDetailsResponse);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.d(CLASS_NAME, throwable.getMessage());
                            }
                        });

        this.compositeDisposable.add(disposable);
    }

    public void loadMovieReviews(int id) {
        Disposable disposable = this.apiService
                .getMovieReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<MovieReviewsResponse, List<Review>>() {
                    @Override
                    public List<Review> apply(MovieReviewsResponse movieReviewsResponse) throws Throwable {
                        return movieReviewsResponse.getReviews();
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        isMovieDetailsLoading.setValue(true);
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Throwable {
                        isMovieDetailsLoading.setValue(false);
                    }
                })
                .subscribe(new Consumer<List<Review>>() {
                               @Override
                               public void accept(List<Review> reviews) throws Throwable {
                                   movieReviews.setValue(reviews);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.d(CLASS_NAME, throwable.getMessage());
                            }
                        });

        this.compositeDisposable.add(disposable);
    }

    public LiveData<Boolean> getIsMovieDetailsLoading() {
        return this.isMovieDetailsLoading;
    }

    public LiveData<MovieDetailsResponse> getMovieDetails() {
        return this.movieDetails;
    }

    public LiveData<List<Review>> getMovieReviews() {
        return this.movieReviews;
    }

    public LiveData<MoviePreview> getFavoriteMovie(int id) {
        return this.moviesDao.getFavoriteMovieById(id);
    }

    public void saveToFavorite(MoviePreview movie) {
        Disposable disposable = this.moviesDao
                .createFavoriteMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();

        this.compositeDisposable.add(disposable);
    }

    public void deleteFromFavorite(int id) {
        Disposable disposable = this.moviesDao
                .deleteFavoriteMovie(id)
                .subscribeOn(Schedulers.io())
                .subscribe();

        this.compositeDisposable.add(disposable);
    }
}
