package com.mhamed.mymoviecompanion.model;

public enum MoviesFilterType {
    POPULAR("Popular"),
    NOW_PLAYING("Now Playing"),
    TOP_RATED("Top Rated");

    private final String name;

    MoviesFilterType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
