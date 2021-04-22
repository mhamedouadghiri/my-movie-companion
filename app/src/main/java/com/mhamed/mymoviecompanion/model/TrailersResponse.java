package com.mhamed.mymoviecompanion.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailersResponse {

    @SerializedName("results")
    private List<Trailer> trailers;

    public TrailersResponse() {
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }
}
