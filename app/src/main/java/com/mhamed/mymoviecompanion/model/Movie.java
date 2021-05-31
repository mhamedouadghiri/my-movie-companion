package com.mhamed.mymoviecompanion.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.mhamed.mymoviecompanion.util.DateTypeAdapter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Movie implements Serializable {

    private Long id;

    @SerializedName(value = "imdb_id")
    private String imdbId;

    @SerializedName(value = "poster_path")
    private String posterPath;

    @SerializedName(value = "backdrop_path")
    private String backdropPath;

    @SerializedName(value = "release_date")
    @JsonAdapter(DateTypeAdapter.class)
    private Date releaseDate;

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

    @SerializedName("genre_ids")
    private List<Long> genreIds;

    private List<Genre> genres;

    @SerializedName("videos")
    private VideosResponse videosResponse;

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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDateFormatted() {
        return releaseDate != null ? new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(releaseDate) : null;
    }

    public int getReleaseYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(releaseDate);
        return calendar.get(Calendar.YEAR);
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

    public String getStringHalfVoteAverage() {
        return String.format(Locale.UK, "%.1f", voteAverage * .5);
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

    public List<Long> getGenreIds() {
        if(genreIds != null)
            return genreIds;
        else return this.genres.stream().map(genre->genre.getId()).collect(Collectors.toList());
    }

    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<String> getGenreNames() {
        return genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
    }

    public VideosResponse getVideosResponse() {
        return videosResponse;
    }

    public void setVideosResponse(VideosResponse videosResponse) {
        this.videosResponse = videosResponse;
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
