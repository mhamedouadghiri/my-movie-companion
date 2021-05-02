package com.mhamed.mymoviecompanion.viewmodel;

import com.mhamed.mymoviecompanion.model.MoviesFilterType;

public class PopularMoviesViewModel extends BaseViewModel {
    public PopularMoviesViewModel() {
        super(MoviesFilterType.POPULAR);
    }
}
