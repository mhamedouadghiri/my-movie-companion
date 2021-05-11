package com.mhamed.mymoviecompanion.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mhamed.mymoviecompanion.entity.WatchedMovies;

import java.util.List;

@Dao
public interface WatchedMoviesDAO {

    @Insert
    void insertWatchedMovies(WatchedMovies watchedMovies);

    @Query("SELECT * From WatchedMovies where userId=(:userId) ")
    LiveData<List<WatchedMovies>> getAllWatchedMoviesByUserId(Long userId);
}
