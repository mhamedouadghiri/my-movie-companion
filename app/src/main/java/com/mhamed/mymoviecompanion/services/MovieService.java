package com.mhamed.mymoviecompanion.services;

import com.mhamed.mymoviecompanion.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieService {

    @GET("movie/{id}")
    Call<Movie> getMovieDetails(@Path("id") Long id);
}
