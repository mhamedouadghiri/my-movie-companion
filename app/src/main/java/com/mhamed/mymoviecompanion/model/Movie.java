package com.mhamed.mymoviecompanion.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

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
    private Double voteAverage;

    private Double popularity;

    @SerializedName(value = "vote_count")
    private Long voteCount;

    private Integer runtime;

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

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public void setGenres(Genre[] genres) {
        this.genres = genres;
    }

    public TrailersResponse getTrailersResponse() {
        return trailersResponse;
    }

    public void setTrailersResponse(TrailersResponse trailersResponse) {
        this.trailersResponse = trailersResponse;
    }

    public CreditsResponse getCreditsResponse() {
        return creditsResponse;
    }

    public void setCreditsResponse(CreditsResponse creditsResponse) {
        this.creditsResponse = creditsResponse;
    }

    public ReviewsResponse getReviewsResponse() {
        return reviewsResponse;
    }

    public void setReviewsResponse(ReviewsResponse reviewsResponse) {
        this.reviewsResponse = reviewsResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
