package com.mhamed.mymoviecompanion.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mhamed.mymoviecompanion.dao.WatchedMoviesDAO;
import com.mhamed.mymoviecompanion.database.ConfigDatabase;
import com.mhamed.mymoviecompanion.entity.WatchedMovie;

import java.util.List;

public class WatchedMoviesRepository {

    private ConfigDatabase database;
    private WatchedMoviesDAO watchedMoviesDAO;

    public WatchedMoviesRepository(Application application) {
        database = ConfigDatabase.getInstance(application);
        watchedMoviesDAO = database.watchedMoviesDAO();
    }

    public LiveData<List<WatchedMovie>> getAllWatchedMoviesByUserId(Long userId) {
        return watchedMoviesDAO.getAllWatchedMoviesByUserId(userId);
    }

    public void insertWatchedMovie(WatchedMovie watchedMovie) {
        new InsertFilmsVusAsyncTask(watchedMoviesDAO).execute(watchedMovie);
    }

    public LiveData<WatchedMovie> getWatchedMovieByUserIdAndMovieId(Long userId, Long movieId) {
        return watchedMoviesDAO.getWatchedMovieByUserIdAndMovieId(userId, movieId);
    }

    private static class InsertFilmsVusAsyncTask extends AsyncTask<WatchedMovie, Void, Void> {
        private WatchedMoviesDAO watchedMoviesDAO;

        private InsertFilmsVusAsyncTask(WatchedMoviesDAO watchedMoviesDAO) {
            this.watchedMoviesDAO = watchedMoviesDAO;
        }

        @Override
        protected Void doInBackground(WatchedMovie... watchedMovies) {
            watchedMoviesDAO.insertWatchedMovie(watchedMovies[0]);
            return null;
        }
    }
}
