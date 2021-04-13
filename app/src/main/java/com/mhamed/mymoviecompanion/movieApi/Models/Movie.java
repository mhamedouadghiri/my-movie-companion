package com.mhamed.mymoviecompanion.movieApi.Models;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Movie {

    private Genre[] genres;
    private Long id;
    @SerializedName(value= "imdb_id")
    private String imdbId;
    @SerializedName(value= "poster_path")
    private String posterPath;
    @SerializedName(value= "backdrop_path")
    private String backdropPath;
    @SerializedName(value= "release_date")
    private String releaseDate;
    private String status;
    private String title;
    @SerializedName(value= "vote_average")
    private double voteAverage;
    private double popularity;
    @SerializedName(value= "vote_count")
    private Long voteCount;
    private int runtime;
    private String overview;
    @SerializedName(value= "original_language")
    private String originalLanguage;
    @SerializedName(value= "original_title")
    private String originalTitle;
    private String homepage;

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


}
