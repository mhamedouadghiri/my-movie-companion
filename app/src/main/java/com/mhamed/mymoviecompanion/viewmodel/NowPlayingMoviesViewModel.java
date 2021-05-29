package com.mhamed.mymoviecompanion.viewmodel;

import com.mhamed.mymoviecompanion.model.MoviesFilterType;

public class NowPlayingMoviesViewModel extends MovieViewModel {
    public NowPlayingMoviesViewModel() {
        super(MoviesFilterType.NOW_PLAYING);
    }
}
