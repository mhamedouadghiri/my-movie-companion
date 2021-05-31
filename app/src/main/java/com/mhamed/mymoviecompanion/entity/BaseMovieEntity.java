package com.mhamed.mymoviecompanion.entity;

import androidx.annotation.NonNull;

public abstract class BaseMovieEntity {

    @NonNull
    protected Long userId;

    @NonNull
    protected String movieId;

    @NonNull
    protected String title;

    @NonNull
    protected String posterPath;

    public BaseMovieEntity(@NonNull Long userId, @NonNull String movieId, @NonNull String title, @NonNull String posterPath) {
        this.userId = userId;
        this.movieId = movieId;
        this.title = title;
        this.posterPath = posterPath;
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
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(@NonNull String posterPath) {
        this.posterPath = posterPath;
    }
}
