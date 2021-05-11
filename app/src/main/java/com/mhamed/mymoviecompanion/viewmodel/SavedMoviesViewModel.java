package com.mhamed.mymoviecompanion.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mhamed.mymoviecompanion.entity.SavedMovies;
import com.mhamed.mymoviecompanion.repository.SavedMoviesRepository;

import java.util.List;


public class SavedMoviesViewModel extends ViewModel {

    private SavedMoviesRepository savedMoviesRepository;

    public void init(Application application) {
        savedMoviesRepository = new SavedMoviesRepository(application);
    }

    public void insertSavedMovies(SavedMovies savedMovies) {
        savedMoviesRepository.insertSavedMovies(savedMovies);
    }

    public LiveData<List<SavedMovies>> getAllSavedMoviesByUserId(Long userId) {
        return savedMoviesRepository.getAllSavedMoviesByUserId(userId);
    }
}
