package com.mhamed.mymoviecompanion.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.Resource;
import com.mhamed.mymoviecompanion.remote.paging.MovieGenreDataSourceFactory;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieGenreViewModel extends BaseViewModel {

    private static final int PAGE_SIZE = 20;

    private final LiveData<PagedList<Movie>> pagedList;
    private final LiveData<Resource> networkState;
    private final MovieGenreDataSourceFactory sourceFactory;

    public MovieGenreViewModel(Long genreId) {
        Executor executor = Executors.newFixedThreadPool(5);

        sourceFactory = new MovieGenreDataSourceFactory(genreId);

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

    @Override
    public void retry() {
        Objects.requireNonNull(sourceFactory.sourceLiveData.getValue()).retryCallback.invoke();
    }
}
