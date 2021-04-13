package com.mhamed.mymoviecompanion.movieApi;

import com.mhamed.mymoviecompanion.movieApi.Models.Movie;
import com.mhamed.mymoviecompanion.movieApi.Services.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

//get movie asynchronously

public class ApiAsync implements Callback<Movie> {

    public void start(Long id){
        MovieService movieService = ApiClient.getInstance();
        Call<Movie> call = movieService.getMovieDetails( id );
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Movie> call, Response<Movie> response) {
        if (response.isSuccessful()) {
            Movie movie = response.body();
            Timber.tag("movie").i(movie.getTitle());
        } else {
            Timber.tag("errorResponse").e(String.valueOf(response.errorBody()));
        }
    }

    @Override
    public void onFailure(Call<Movie> call, Throwable t) {
        t.printStackTrace();
    }

}