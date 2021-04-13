package com.mhamed.mymoviecompanion.movieApi;

import android.util.Log;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mhamed.mymoviecompanion.movieApi.Models.Movie;
import com.mhamed.mymoviecompanion.movieApi.Services.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class Controller implements Callback<Movie> {


    public void start() {
        MovieService movieService = ApiClient.getInstance();

        Call<Movie> call = movieService.getMovieDetails(3);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<Movie> call, Response<Movie> response) {
        if(response.isSuccessful()) {
            Movie movie = response.body();
            Timber.tag("movietitle").i(movie.getTitle());
        } else {
            Timber.tag("erreureponse").e(String.valueOf(response.errorBody()));
        }
    }

    @Override
    public void onFailure(Call<Movie> call, Throwable t) {
        t.printStackTrace();
    }
}