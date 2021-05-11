package com.mhamed.mymoviecompanion.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mhamed.mymoviecompanion.dao.WatchedMoviesDAO;
import com.mhamed.mymoviecompanion.database.ConfigDatabase;
import com.mhamed.mymoviecompanion.entity.WatchedMovies;

import java.util.List;

public class WatchedMoviesRepository {

    private ConfigDatabase database;
    private WatchedMoviesDAO watchedMoviesDAO;

    public WatchedMoviesRepository(Application application) {
        database = ConfigDatabase.getInstance(application);
        watchedMoviesDAO = database.watchedMoviesDAO();
    }

    public LiveData<List<WatchedMovies>> getAllWatchedMoviesByUserId(Long userId) {
        return watchedMoviesDAO.getAllWatchedMoviesByUserId(userId);
    }

    public void insertWatchedMovies(WatchedMovies watchedMovies) {
        new InsertFilmsVusAsyncTask(watchedMoviesDAO).execute(watchedMovies);
    }

    private static class InsertFilmsVusAsyncTask extends AsyncTask<WatchedMovies, Void, Void> {
        private WatchedMoviesDAO watchedMoviesDAO;

        private InsertFilmsVusAsyncTask(WatchedMoviesDAO watchedMoviesDAO) {
            this.watchedMoviesDAO = watchedMoviesDAO;
        }

        @Override
        protected Void doInBackground(WatchedMovies... watchedMovies) {
            watchedMoviesDAO.insertWatchedMovies(watchedMovies[0]);
            return null;
        }
    }
}
