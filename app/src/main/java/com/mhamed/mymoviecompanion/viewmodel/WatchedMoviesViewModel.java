package com.mhamed.mymoviecompanion.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mhamed.mymoviecompanion.entity.WatchedMovie;
import com.mhamed.mymoviecompanion.repository.WatchedMoviesRepository;

import java.util.List;

public class WatchedMoviesViewModel extends ViewModel {

    private WatchedMoviesRepository watchedMoviesRepository;

    public void init(Application application) {
        watchedMoviesRepository = new WatchedMoviesRepository(application);
    }

    public void insertWatchedMovie(WatchedMovie watchedMovie) {
        watchedMoviesRepository.insertWatchedMovie(watchedMovie);
    }

    public LiveData<List<WatchedMovie>> getAllWatchedMoviesByUserId(Long userId) {
        return watchedMoviesRepository.getAllWatchedMoviesByUserId(userId);
    }

    public LiveData<WatchedMovie> getWatchedMovieByUserIdAndMovieId(Long userId, Long movieId) {
        return watchedMoviesRepository.getWatchedMovieByUserIdAndMovieId(userId, movieId);
    }
}
