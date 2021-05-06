package com.mhamed.mymoviecompanion.remote.api;

import com.mhamed.mymoviecompanion.model.CreditsResponse;
import com.mhamed.mymoviecompanion.model.MoviesResponse;
import com.mhamed.mymoviecompanion.model.ReviewsResponse;
import com.mhamed.mymoviecompanion.model.TrailersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("page") int page);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("page") int page);

    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlayingMovies(@Query("page") int page);

//    @GET("movie/{id}?append_to_response=videos,credits,reviews")
//    Call<Movie> getMovieDetails(@Path("id") long id);

    @GET("movie/{id}/credits")
    Call<CreditsResponse> getCast(@Path("id") long id);

    @GET("movie/{id}/videos")
    Call<TrailersResponse> getVideos(@Path("id") long id);

    @GET("movie/{id}/reviews")
    Call<ReviewsResponse> getReviews(@Path("id") long id);

    @GET("/discover/movie")
    Call<MoviesResponse> SearchMovies(@Query("page") int page,
                                      @Query("sort_by") String sort_by,
                                      @Query("with_genres") String genres,
                                      @Query("release_date.gte") String release_date_gte,
                                      @Query("release_date.lte") String release_date_lte,
                                      @Query("year") String year
    );

    @GET("/search/movie")
    Call<MoviesResponse> getMovieByTitle(@Query("query") String title
    );
}
