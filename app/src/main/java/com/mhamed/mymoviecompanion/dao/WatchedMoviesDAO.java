package com.mhamed.mymoviecompanion.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mhamed.mymoviecompanion.entity.WatchedMovie;

import java.util.List;

@Dao
public interface WatchedMoviesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWatchedMovie(WatchedMovie watchedMovie);

    @Query("SELECT * From WatchedMovie where userId=(:userId) ")
    LiveData<List<WatchedMovie>> getAllWatchedMoviesByUserId(Long userId);

    @Query("select * from WatchedMovie where userId=(:userId) and movieId=(:movieId)")
    LiveData<WatchedMovie> getWatchedMovieByUserIdAndMovieId(Long userId, Long movieId);
}
