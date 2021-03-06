package com.mhamed.mymoviecompanion.remote.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.MoviesFilterType;
import com.mhamed.mymoviecompanion.model.MoviesResponse;
import com.mhamed.mymoviecompanion.model.Resource;
import com.mhamed.mymoviecompanion.remote.api.ApiClient;
import com.mhamed.mymoviecompanion.remote.api.MovieService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePageKeyedDataSource extends PageKeyedDataSource<Integer, Movie> {

    private static final int FIRST_PAGE = 1;
    private final MovieService movieService = ApiClient.getInstance();
    private final MoviesFilterType sortBy;
    public MutableLiveData<Resource> networkState = new MutableLiveData<>();
    public RetryCallback retryCallback = null;

    public MoviePageKeyedDataSource(MoviesFilterType sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, Movie> callback) {
        networkState.postValue(Resource.loading(null));

        // load data from API
        Call<MoviesResponse> request;
        if (sortBy == MoviesFilterType.POPULAR) {
            request = movieService.getPopularMovies(FIRST_PAGE);
        } else if (sortBy == MoviesFilterType.TOP_RATED) {
            request = movieService.getTopRatedMovies(FIRST_PAGE);
        } else {
            request = movieService.getNowPlayingMovies(FIRST_PAGE);
        }

        // we execute sync since this is triggered by refresh
        try {
            Response<MoviesResponse> response = request.execute();
            MoviesResponse data = response.body();
            List<Movie> movieList = data != null ? data.getMovies() : Collections.emptyList();

            retryCallback = null;
            networkState.postValue(Resource.success(null));
            callback.onResult(movieList, null, FIRST_PAGE + 1);
        } catch (IOException e) {
            // publish error
            networkState.postValue(Resource.error(e.getMessage(), null));
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, Movie> callback) {
        // ignored, since we only ever append to our initial load
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, Movie> callback) {
        networkState.postValue(Resource.loading(null));

        // load data from API
        Call<MoviesResponse> request;
        if (sortBy == MoviesFilterType.POPULAR) {
            request = movieService.getPopularMovies(params.key);
        } else if (sortBy == MoviesFilterType.TOP_RATED) {
            request = movieService.getTopRatedMovies(params.key);
        } else {
            request = movieService.getNowPlayingMovies(params.key);
        }

        request.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    MoviesResponse data = response.body();
                    List<Movie> movieList = data != null ? data.getMovies() : Collections.emptyList();

                    retryCallback = null;
                    callback.onResult(movieList, params.key + 1);
                    networkState.postValue(Resource.success(null));
                } else {
                    retryCallback = () -> loadAfter(params, callback);
                    networkState.postValue(Resource.error("error code: " + response.code(), null));
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                networkState.postValue(Resource.error(t != null ? t.getMessage() : "unknown error", null));
            }
        });
    }

    public interface RetryCallback {
        void invoke();
    }
}
