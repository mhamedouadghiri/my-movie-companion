package com.mhamed.mymoviecompanion.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.io.Serializable;

@Entity(foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")},
        primaryKeys = {"movieId", "userId"})
public class SavedMovie implements Serializable {

    @NonNull
    private Long userId;

    @NonNull
    private String movieId;

    @NonNull
    private Boolean saved;

    public SavedMovie(@NonNull Long userId, @NonNull String movieId, @NonNull Boolean saved) {
        this.userId = userId;
        this.movieId = movieId;
        this.saved = saved;
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

    @NonNull
    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(@NonNull Boolean saved) {
        this.saved = saved;
    }
}
