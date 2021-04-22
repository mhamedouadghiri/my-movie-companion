package com.mhamed.mymoviecompanion.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Objects;

public class Movie {

    private Long id;
    @SerializedName(value = "imdb_id")
    private String imdbId;
    @SerializedName(value = "poster_path")
    private String posterPath;
    @SerializedName(value = "backdrop_path")
    private String backdropPath;
    @SerializedName(value = "release_date")
    private String releaseDate;
    private String status;
    private String title;
    @SerializedName(value = "vote_average")
    private double voteAverage;
    private double popularity;
    @SerializedName(value = "vote_count")
    private Long voteCount;
    private int runtime;
    private String overview;
    @SerializedName(value = "original_language")
    private String originalLanguage;
    @SerializedName(value = "original_title")
    private String originalTitle;
    private String homepage;
    private Genre[] genres;
    @SerializedName("videos")
    private TrailersResponse trailersResponse;
    @SerializedName("credits")
    private CreditsResponse creditsResponse;
    @SerializedName("reviews")
    private ReviewsResponse reviewsResponse;

    // TODO: remove this
    // ############################# begin #############################
    private int posterImage;
    private int backdropImage;

    public int getPosterImage() {
        return posterImage;
    }

    public int getBackdropImage() {
        return backdropImage;
    }

    public Movie(String title, int posterImage, int backdropImage) {
        this.title = title;
        this.posterImage = posterImage;
        this.backdropImage = backdropImage;
    }

    public Movie(String title, int posterImage) {
        this.title = title;
        this.posterImage = posterImage;
    }
    // ############################# end #############################

    public Movie() {
    }

    public Genre[] getGenres() {
        return genres;
    }

    public Long getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public int getRuntime() {
        return runtime;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public double getPopularity() {
        return popularity;
    }

    public TrailersResponse getTrailersResponse() {
        return trailersResponse;
    }

    public CreditsResponse getCreditsResponse() {
        return creditsResponse;
    }

    public ReviewsResponse getReviewsResponse() {
        return reviewsResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.voteAverage, voteAverage) == 0 &&
                Double.compare(movie.popularity, popularity) == 0 &&
                runtime == movie.runtime &&
                Objects.equals(id, movie.id) &&
                Objects.equals(imdbId, movie.imdbId) &&
                Objects.equals(posterPath, movie.posterPath) &&
                Objects.equals(backdropPath, movie.backdropPath) &&
                Objects.equals(releaseDate, movie.releaseDate) &&
                Objects.equals(status, movie.status) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(overview, movie.overview) &&
                Objects.equals(originalLanguage, movie.originalLanguage) &&
                Objects.equals(originalTitle, movie.originalTitle);

    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, imdbId, posterPath, backdropPath, releaseDate, status, title, voteAverage, popularity, voteCount, runtime, overview, originalLanguage, originalTitle, homepage);
        result = 31 * result + Arrays.hashCode(genres);
        return result;
    }
}
