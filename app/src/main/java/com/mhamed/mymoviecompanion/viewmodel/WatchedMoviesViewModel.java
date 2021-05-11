package com.mhamed.mymoviecompanion.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mhamed.mymoviecompanion.entity.WatchedMovies;
import com.mhamed.mymoviecompanion.repository.WatchedMoviesRepository;

import java.util.List;


public class WatchedMoviesViewModel extends ViewModel {

    private WatchedMoviesRepository watchedMoviesRepository;

    public void init(Application application) {
        watchedMoviesRepository = new WatchedMoviesRepository(application);
    }

    public void insertWatchedMovies(WatchedMovies watchedMovies) {
        Log.i("FilmsModel", watchedMovies.getReview());
        watchedMoviesRepository.insertWatchedMovies(watchedMovies);
    }

    public LiveData<List<WatchedMovies>> getAllWatchedMoviesByUserId(Long userId) {
        return watchedMoviesRepository.getAllWatchedMoviesByUserId(userId);
    }
}
