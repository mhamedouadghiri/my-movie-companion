package com.mhamed.mymoviecompanion.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.MoviesFilterType;
import com.mhamed.mymoviecompanion.model.Resource;
import com.mhamed.mymoviecompanion.remote.paging.MovieDataSourceFactory;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BaseViewModel extends ViewModel {

    private static final int PAGE_SIZE = 20;

    private final LiveData<PagedList<Movie>> pagedList;
    private final LiveData<Resource> networkState;
    private final MovieDataSourceFactory sourceFactory;

    public BaseViewModel(MoviesFilterType moviesFilterType) {
        Executor executor = Executors.newFixedThreadPool(5);

        sourceFactory = new MovieDataSourceFactory(moviesFilterType);

        // paging configuration
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(PAGE_SIZE)
                .build();

        // Get the paged list
        pagedList = new LivePagedListBuilder<>(sourceFactory, config)
                .setFetchExecutor(executor)
                .build();

        networkState = Transformations.switchMap(sourceFactory.sourceLiveData, input -> input.networkState);
    }

    public LiveData<PagedList<Movie>> getPagedList() {
        return pagedList;
    }

    public LiveData<Resource> getNetworkState() {
        return networkState;
    }

    public void retry() {
        Objects.requireNonNull(sourceFactory.sourceLiveData.getValue()).retryCallback.invoke();
    }
}
