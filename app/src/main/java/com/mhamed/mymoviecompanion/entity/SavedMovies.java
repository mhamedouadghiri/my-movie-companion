package com.mhamed.mymoviecompanion.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.io.Serializable;

@Entity(foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")},
        primaryKeys = {"movieId", "userId"})
public class SavedMovies implements Serializable {

    @NonNull
    private Long userId;

    @NonNull
    private String movieId;

    public SavedMovies(Long userId, String movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    @NonNull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(@NonNull Long userId) {
        this.userId = userId;
    }

    @NonNull
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(@NonNull String movieId) {
        this.movieId = movieId;
    }
}
