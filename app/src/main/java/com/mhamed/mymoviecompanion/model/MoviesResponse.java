package com.mhamed.mymoviecompanion.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("page")
    private int page = 0;

    @SerializedName("total_results")
    private int totalResults = 0;

    @SerializedName("total_pages")
    private int  totalPages = 0;

    @SerializedName("results")
    private List<Movie> movies;

    public MoviesResponse(int page, int totalResults, int totalPages, List<Movie> movies) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.movies = movies;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
