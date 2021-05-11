package com.mhamed.mymoviecompanion.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.io.Serializable;

@Entity(foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")},
        primaryKeys = {"movieId", "userId"})
public class WatchedMovies implements Serializable {

    @NonNull
    private Long userId;

    @NonNull
    private String movieId;

    private Float vote;

    private String review;

    public WatchedMovies(Long userId, String movieId, float vote, String review) {
        this.userId = userId;
        this.movieId = movieId;
        this.vote = vote;
        this.review = review;
    }

    @NonNull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(@NonNull Long userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Float getVote() {
        return vote;
    }

    public void setVote(Float vote) {
        this.vote = vote;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
