package com.mhamed.mymoviecompanion.model;

public enum MoviesFilterType {
    POPULAR("Popular"),
    TOP_RATED("Top Rated"),
    NOW_PLAYING("Now Playing");

    private final String name;

    MoviesFilterType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
