package com.mhamed.mymoviecompanion.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.io.Serializable;

@Entity(foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")},
        primaryKeys = {"movieId", "userId"})
public class WatchedMovie extends BaseMovieEntity implements Serializable {

    @NonNull
    private Float rating;

    public WatchedMovie(@NonNull Long userId, @NonNull String movieId, @NonNull Float rating, @NonNull String title, @NonNull String posterPath) {
        super(userId, movieId, title, posterPath);
        this.rating = rating;
    }

    @NonNull
    public Float getRating() {
        return rating;
    }

    public void setRating(@NonNull Float rating) {
        this.rating = rating;
    }
}
