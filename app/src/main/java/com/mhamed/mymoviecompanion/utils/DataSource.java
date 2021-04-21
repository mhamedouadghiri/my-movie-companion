package com.mhamed.mymoviecompanion.utils;

import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static List<Movie> getPopularMovies() {
        List<Movie> lstMovies = new ArrayList<>();
        lstMovies.add(new Movie("Moana", R.drawable.moana, R.drawable.spidercover));
        lstMovies.add(new Movie("Black P", R.drawable.blackp, R.drawable.spidercover));
        lstMovies.add(new Movie("The Martian", R.drawable.themartian));
        lstMovies.add(new Movie("The Martian", R.drawable.themartian));
        lstMovies.add(new Movie("The Martian", R.drawable.themartian));
        lstMovies.add(new Movie("The Martian", R.drawable.themartian));
        return lstMovies;
    }

    public static List<Movie> getWeekMovies() {
        List<Movie> lstMovies = new ArrayList<>();
        lstMovies.add(new Movie("Moana", R.drawable.moana, R.drawable.spidercover));
        lstMovies.add(new Movie("Black P", R.drawable.blackp, R.drawable.spidercover));
        lstMovies.add(new Movie("The Martian", R.drawable.themartian));
        lstMovies.add(new Movie("The Martian", R.drawable.themartian));
        lstMovies.add(new Movie("The Martian", R.drawable.themartian));
        lstMovies.add(new Movie("The Martian", R.drawable.themartian));
        return lstMovies;
    }
}
