package com.mhamed.mymoviecompanion.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mhamed.mymoviecompanion.entity.SavedMovies;

import java.util.List;

@Dao
public interface SavedMoviesDAO {

    @Insert
    void insertSavedMovies(SavedMovies savedMovies);

    @Query("SELECT * From SavedMovies where userId=(:userId) ")
    LiveData<List<SavedMovies>> getAllSavedMoviesByUserId(Long userId);
}
