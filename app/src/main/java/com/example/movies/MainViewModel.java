package com.example.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private static final String CLASS_NAME = "MainViewModel";

    private final ApiService apiService;

    private final MutableLiveData<List<MoviePreview>> movies;
    private final MutableLiveData<Boolean> isMoviesLoading;

    private final CompositeDisposable compositeDisposable;

    private int page = 1;

    public MainViewModel(@NonNull Application application) {
        super(application);

        this.apiService = ApiFactory.apiService;
        this.movies = new MutableLiveData<>();
        this.isMoviesLoading = new MutableLiveData<>(false);
        this.compositeDisposable = new CompositeDisposable();

        this.loadMovies();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        this.compositeDisposable.dispose();
    }

    public void loadMovies() {
        Boolean isLoading = isMoviesLoading.getValue();

        if (isLoading != null && isLoading) {
            return;
        }

        Disposable disposable = this.apiService
                .getMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        isMoviesLoading.setValue(true);
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Throwable {
                        isMoviesLoading.setValue(false);
                    }
                })
                .subscribe(
                        new Consumer<MoviesResponse>() {
                            @Override
                            public void accept(MoviesResponse moviesResponse) throws Throwable {
                                List<MoviePreview> loadedMovies = movies.getValue();
                                List<MoviePreview> newMovies = moviesResponse.getMovies();

                                if (loadedMovies != null) {
                                    loadedMovies.addAll(newMovies);
                                    movies.setValue(loadedMovies);
                                } else {
                                    movies.setValue(newMovies);
                                }

                                page++;
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.d(CLASS_NAME, throwable.getMessage());
                            }
                        });

        this.compositeDisposable.add(disposable);
    }

    public LiveData<List<MoviePreview>> getMovies() {
        return this.movies;
    }

    public LiveData<Boolean> getIsMoviesLoading() {
        return this.isMoviesLoading;
    }
}
