package com.mhamed.mymoviecompanion.remote.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.MoviesResponse;
import com.mhamed.mymoviecompanion.model.Resource;
import com.mhamed.mymoviecompanion.remote.api.ApiClient;
import com.mhamed.mymoviecompanion.remote.api.MovieService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieGenrePageKeyedDataSource extends PageKeyedDataSource<Integer, Movie> {

    private static final int FIRST_PAGE = 1;

    private final MovieService movieService = ApiClient.getInstance();
    private final Long genreId;

    public MutableLiveData<Resource> networkState = new MutableLiveData<>();
    public RetryCallback retryCallback = null;

    String currentDate;

    public MovieGenrePageKeyedDataSource(Long genreId) {
        this.genreId = genreId;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = sdf.format(new Date());
    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Movie> callback) {
        networkState.postValue(Resource.loading(null));

        Call<MoviesResponse> request = movieService.discoverMoviesWithFilters(FIRST_PAGE, "release_date.desc", String.valueOf(genreId), null, currentDate, null);

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
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Movie> callback) {
        networkState.postValue(Resource.loading(null));

        Call<MoviesResponse> request = movieService.discoverMoviesWithFilters(params.key, "release_date.desc", String.valueOf(genreId), null, currentDate, null);

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
