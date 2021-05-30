package com.mhamed.mymoviecompanion.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mhamed.mymoviecompanion.entity.SavedMovie;

import java.util.List;

@Dao
public interface SavedMoviesDAO {

    @Insert
    void insertSavedMovie(SavedMovie savedMovie);

    @Query("SELECT * From SavedMovie where userId=(:userId) ")
    LiveData<List<SavedMovie>> getAllSavedMoviesByUserId(Long userId);
}
