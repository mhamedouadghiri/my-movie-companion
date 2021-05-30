package com.mhamed.mymoviecompanion.remote.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.DataSource;

import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.viewmodel.MovieGenreViewModel;

public class MovieGenreDataSourceFactory extends DataSource.Factory<Integer, Movie> implements ViewModelProvider.Factory {

    private final Long genreId;
    public MutableLiveData<MovieGenrePageKeyedDataSource> sourceLiveData = new MutableLiveData<>();

    public MovieGenreDataSourceFactory(Long genreId) {
        this.genreId = genreId;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        MovieGenrePageKeyedDataSource movieDataSource = new MovieGenrePageKeyedDataSource(genreId);
        sourceLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieGenreViewModel(genreId);
    }
}
