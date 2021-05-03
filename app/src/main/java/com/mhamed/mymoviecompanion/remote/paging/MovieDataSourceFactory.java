package com.mhamed.mymoviecompanion.remote.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.MoviesFilterType;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    private final MoviesFilterType sortBy;
    public MutableLiveData<MoviePageKeyedDataSource> sourceLiveData = new MutableLiveData<>();

    public MovieDataSourceFactory(MoviesFilterType sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        MoviePageKeyedDataSource movieDataSource = new MoviePageKeyedDataSource(sortBy);
        sourceLiveData.postValue(movieDataSource);
        return movieDataSource;
    }
}
