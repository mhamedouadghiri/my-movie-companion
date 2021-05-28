package com.mhamed.mymoviecompanion.viewmodel;

import com.mhamed.mymoviecompanion.model.MoviesFilterType;

public class TopRatedMoviesViewModel extends MovieViewModel {
    public TopRatedMoviesViewModel() {
        super(MoviesFilterType.TOP_RATED);
    }
}
