package com.example.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailsViewModel extends AndroidViewModel {

    private static final String CLASS_NAME = "MovieDetailsViewModel";

    private final ApiService apiService;

    private final MutableLiveData<MovieDetailsResponse> movieDetails;
    private final MutableLiveData<Boolean> isMovieDetailsLoading;

    private final CompositeDisposable compositeDisposable;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);

        this.apiService = ApiFactory.apiService;
        this.movieDetails = new MutableLiveData<>();
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

    public LiveData<Boolean> getIsMovieDetailsLoading() {
        return this.isMovieDetailsLoading;
    }

    public LiveData<MovieDetailsResponse> getMovieDetails() {
        return this.movieDetails;
    }
}
