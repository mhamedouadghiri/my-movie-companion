package com.mhamed.mymoviecompanion.movieApi.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Movie {

    private GenreDTO[] genres;
    private int id;
    private String imdb_id;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private String status;
    private String title;
    private double vote_average;
    private double popularity;
    private int vote_count;
    private int runtime;
    private String overview;
    private String original_language;
    private String original_title;
    private String homepage;

    public GenreDTO[] getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public String getImdbId() {
        return imdb_id;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public double getVoteAverage() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public int getRuntime() {
        return runtime;
    }

    public int getVoteCount() {
        return vote_count;
    }

    public double getPopularity() {
        return popularity;
    }

    /*
    |--------------------------------------------------------------------------
    | Inner classes
    |--------------------------------------------------------------------------
    */

    /**
     * Genre DTO class
     */
    public static class GenreDTO {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
