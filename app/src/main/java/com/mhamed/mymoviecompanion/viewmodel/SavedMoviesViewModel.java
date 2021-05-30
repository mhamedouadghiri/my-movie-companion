package com.mhamed.mymoviecompanion.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mhamed.mymoviecompanion.entity.SavedMovie;
import com.mhamed.mymoviecompanion.repository.SavedMoviesRepository;

import java.util.List;

public class SavedMoviesViewModel extends ViewModel {

    private SavedMoviesRepository savedMoviesRepository;

    public void init(Application application) {
        savedMoviesRepository = new SavedMoviesRepository(application);
    }

    public void insertSavedMovie(SavedMovie savedMovie) {
        savedMoviesRepository.insertSavedMovie(savedMovie);
    }

    public LiveData<List<SavedMovie>> getAllSavedMoviesByUserId(Long userId) {
        return savedMoviesRepository.getAllSavedMoviesByUserId(userId);
    }

    public LiveData<SavedMovie> getSavedMovieByUserIdAndMovieId(Long userId, Long movieId) {
        return savedMoviesRepository.getSavedMovieByUserIdAndMovieId(userId, movieId);
    }
}
