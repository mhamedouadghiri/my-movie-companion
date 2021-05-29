package com.mhamed.mymoviecompanion.viewmodel;

import com.mhamed.mymoviecompanion.model.MoviesFilterType;

public class PopularMoviesViewModel extends MovieViewModel {
    public PopularMoviesViewModel() {
        super(MoviesFilterType.POPULAR);
    }
}
