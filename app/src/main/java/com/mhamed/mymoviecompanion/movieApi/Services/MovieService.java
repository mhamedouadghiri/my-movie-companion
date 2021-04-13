package com.mhamed.mymoviecompanion.movieApi.Services;


import com.mhamed.mymoviecompanion.movieApi.Models.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieService {

    @GET("movie/{id}")
    Call<Movie> getMovieDetails(@Path("id") long id);
}