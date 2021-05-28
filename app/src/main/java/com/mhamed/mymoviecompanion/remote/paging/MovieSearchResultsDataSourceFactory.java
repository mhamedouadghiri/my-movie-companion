package com.mhamed.mymoviecompanion.remote.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.DataSource;

import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.viewmodel.SearchResultsViewModel;

public class MovieSearchResultsDataSourceFactory extends DataSource.Factory<Integer, Movie> implements ViewModelProvider.Factory {

    private final String query;
    public MutableLiveData<MovieSearchResultsPageKeyedDataSource> sourceLiveData = new MutableLiveData<>();

    public MovieSearchResultsDataSourceFactory(String query) {
        this.query = query;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        MovieSearchResultsPageKeyedDataSource movieDataSource = new MovieSearchResultsPageKeyedDataSource(query);
        sourceLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SearchResultsViewModel(query);
    }
}
