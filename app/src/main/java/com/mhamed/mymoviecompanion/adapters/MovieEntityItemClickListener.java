package com.mhamed.mymoviecompanion.adapters;

import android.widget.ImageView;

import com.mhamed.mymoviecompanion.entity.BaseMovieEntity;

public interface MovieEntityItemClickListener {

    // we will need the imageView to make the shared animation between the two activity
    void onMovieClick(BaseMovieEntity movie, ImageView movieImageView);
}
