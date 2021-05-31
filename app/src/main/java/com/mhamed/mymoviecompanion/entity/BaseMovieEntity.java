package com.mhamed.mymoviecompanion.entity;

import androidx.annotation.NonNull;

public class BaseMovieEntity {

    @NonNull
    private String title;

    @NonNull
    private String posterPath;

    public BaseMovieEntity(@NonNull String title, @NonNull String posterPath) {
        this.title = title;
        this.posterPath = posterPath;
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
