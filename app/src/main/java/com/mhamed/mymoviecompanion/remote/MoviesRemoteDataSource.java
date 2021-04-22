package com.mhamed.mymoviecompanion.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.MoviesFilterType;
import com.mhamed.mymoviecompanion.model.RepoMoviesResult;
import com.mhamed.mymoviecompanion.model.Resource;
import com.mhamed.mymoviecompanion.remote.api.MovieService;
import com.mhamed.mymoviecompanion.remote.paging.MovieDataSourceFactory;

import java.util.concurrent.Executor;

import retrofit2.Call;

public class MoviesRemoteDataSource {

    private static final int PAGE_SIZE = 20;
    private static volatile MoviesRemoteDataSource sInstance;
    private final MovieService mMovieService;
    private Executor executor;

    private MoviesRemoteDataSource(MovieService movieService, Executor executor) {
        mMovieService = movieService;
        executor = executor;
    }

    public static MoviesRemoteDataSource getInstance(MovieService movieService, Executor executor) {
        if (sInstance == null) {
            synchronized (MovieService.class) {
                if (sInstance == null) {
                    sInstance = new MoviesRemoteDataSource(movieService, executor);
                }
            }
        }
        return sInstance;
    }

    public Call<Movie> loadMovie(final long movieId) {
        return mMovieService.getMovieDetails(movieId);
    }

    /**
     * Load movies for certain filter.
     */
    public RepoMoviesResult loadMoviesFilteredBy(MoviesFilterType sortBy) {
        MovieDataSourceFactory sourceFactory = new MovieDataSourceFactory(mMovieService, executor, sortBy);

        // paging configuration
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();

        // Get the paged list
        LiveData<PagedList<Movie>> moviesPagedList = new LivePagedListBuilder<>(sourceFactory, config)
                .setFetchExecutor(executor)
                .build();

        LiveData<Resource> networkState = Transformations.switchMap(sourceFactory.sourceLiveData, input -> input.networkState);

        // Get pagedList and network errors exposed to the viewmodel
        return new RepoMoviesResult(
                moviesPagedList,
                networkState,
                sourceFactory.sourceLiveData
        );
    }
}
