package com.mhamed.mymoviecompanion.api;

import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.services.MovieService;

import java.io.IOException;

import retrofit2.Response;
import timber.log.Timber;

public class ApiSync {
    MovieService movieService;

    public void ApiAsync() {
        movieService = ApiClient.getInstance();
    }

    public Movie getMovieDetails(Long id) throws IOException {
        Response<Movie> response = ApiClient.getInstance().getMovieDetails(id).execute();
        if (response.isSuccessful()) {
            Movie movie = response.body();
            return movie;
        } else {
            Timber.tag("erreureponse").e(String.valueOf(response.errorBody()));
            return null;
        }
    }
}
