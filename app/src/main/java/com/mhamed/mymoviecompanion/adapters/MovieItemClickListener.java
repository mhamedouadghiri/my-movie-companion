package com.mhamed.mymoviecompanion.adapters;

import android.widget.ImageView;

import com.mhamed.mymoviecompanion.model.Movie;

public interface MovieItemClickListener {

    // we will need the imageView to make the shared animation between the two activity
    void onMovieClick(Movie movie, ImageView movieImageView);
}
