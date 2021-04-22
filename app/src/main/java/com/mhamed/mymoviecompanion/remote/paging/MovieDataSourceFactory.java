package com.mhamed.mymoviecompanion.remote.paging;

import java.util.concurrent.Executor;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.MoviesFilterType;
import com.mhamed.mymoviecompanion.remote.api.MovieService;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    public MutableLiveData<MoviePageKeyedDataSource> sourceLiveData = new MutableLiveData<>();

    private final MovieService movieService;
    private final Executor networkExecutor;
    private final MoviesFilterType sortBy;

    public MovieDataSourceFactory(MovieService movieService,
                                  Executor networkExecutor, MoviesFilterType sortBy) {
        this.movieService = movieService;
        this.sortBy = sortBy;
        this.networkExecutor = networkExecutor;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        MoviePageKeyedDataSource movieDataSource =
                new MoviePageKeyedDataSource(movieService, networkExecutor, sortBy);
        sourceLiveData.postValue(movieDataSource);
        return movieDataSource;
    }
}