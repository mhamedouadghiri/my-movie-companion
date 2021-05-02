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

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PopularMoviesViewModel extends ViewModel {
    private static final int PAGE_SIZE = 20;


    private LiveData<PagedList<Movie>> pagedList;

    private LiveData<Resource> networkState;
    private Executor executor;
    private MovieDataSourceFactory sourceFactory;


    public PopularMoviesViewModel() {
        executor = Executors.newFixedThreadPool(5);


        sourceFactory = new MovieDataSourceFactory(MoviesFilterType.POPULAR);

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
        sourceFactory.sourceLiveData.getValue().retryCallback.invoke();
    }

}
