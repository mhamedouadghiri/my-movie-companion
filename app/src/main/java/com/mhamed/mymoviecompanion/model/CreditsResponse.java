package com.mhamed.mymoviecompanion.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditsResponse {

    @SerializedName("cast")
    public List<Cast> cast;

    public List<Cast> getCast() {
        return cast;
    }
}
