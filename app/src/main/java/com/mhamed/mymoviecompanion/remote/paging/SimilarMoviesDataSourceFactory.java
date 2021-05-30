package com.mhamed.mymoviecompanion.remote.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.DataSource;

import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.viewmodel.SimilarMoviesViewModel;

public class SimilarMoviesDataSourceFactory extends DataSource.Factory<Integer, Movie> implements ViewModelProvider.Factory {

    private final Long movieId;
    public MutableLiveData<SimilarMoviesPageKeyedDataSource> sourceLiveData = new MutableLiveData<>();

    public SimilarMoviesDataSourceFactory(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        SimilarMoviesPageKeyedDataSource movieDataSource = new SimilarMoviesPageKeyedDataSource(movieId);
        sourceLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SimilarMoviesViewModel(movieId);
    }
}
