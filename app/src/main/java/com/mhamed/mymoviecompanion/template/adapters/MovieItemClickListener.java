package com.mhamed.mymoviecompanion.template.adapters;


import android.widget.ImageView;

import com.mhamed.mymoviecompanion.template.models.Movie;


public interface MovieItemClickListener {

    void onMovieClick(Movie movie, ImageView movieImageView); // we will need the imageview to make the shared animation between the two activity

}