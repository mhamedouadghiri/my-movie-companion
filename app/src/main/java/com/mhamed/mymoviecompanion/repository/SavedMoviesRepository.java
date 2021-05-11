package com.mhamed.mymoviecompanion.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mhamed.mymoviecompanion.dao.SavedMoviesDAO;
import com.mhamed.mymoviecompanion.database.ConfigDatabase;
import com.mhamed.mymoviecompanion.entity.SavedMovies;

import java.util.List;


public class SavedMoviesRepository {

    private ConfigDatabase database;
    private SavedMoviesDAO savedMoviesDAO;

    public SavedMoviesRepository(Application application) {
        database = ConfigDatabase.getInstance(application);
        savedMoviesDAO = database.savedMoviesDAO();
    }

    public LiveData<List<SavedMovies>> getAllSavedMoviesByUserId(Long userId) {
        return savedMoviesDAO.getAllSavedMoviesByUserId(userId);
    }

    public void insertSavedMovies(SavedMovies savedMovies) {
        new InsertFilmsEnviesAsyncTask(savedMoviesDAO).execute(savedMovies);
    }

    private static class InsertFilmsEnviesAsyncTask extends AsyncTask<SavedMovies, Void, Void> {
        private SavedMoviesDAO savedMoviesDAO;

        private InsertFilmsEnviesAsyncTask(SavedMoviesDAO savedMoviesDAO) {
            this.savedMoviesDAO = savedMoviesDAO;
        }

        @Override
        protected Void doInBackground(SavedMovies... filmsEnvies) {
            savedMoviesDAO.insertSavedMovies(filmsEnvies[0]);
            return null;
        }
    }
}
