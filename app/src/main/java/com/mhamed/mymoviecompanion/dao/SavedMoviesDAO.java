package com.mhamed.mymoviecompanion.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mhamed.mymoviecompanion.entity.SavedMovie;

import java.util.List;

@Dao
public interface SavedMoviesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSavedMovie(SavedMovie savedMovie);

    @Query("SELECT * From SavedMovie where userId=(:userId) and saved = 1")
    LiveData<List<SavedMovie>> getAllSavedMoviesByUserId(Long userId);

    @Query("select * from SavedMovie where userId=(:userId) and movieId=(:movieId)")
    LiveData<SavedMovie> getSavedMovieByUserIdAndMovieId(Long userId, Long movieId);
}
