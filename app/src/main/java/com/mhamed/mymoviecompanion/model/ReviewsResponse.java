package com.mhamed.mymoviecompanion.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewsResponse {

    @SerializedName("results")
    private List<Review> reviews;

    public ReviewsResponse() {
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
