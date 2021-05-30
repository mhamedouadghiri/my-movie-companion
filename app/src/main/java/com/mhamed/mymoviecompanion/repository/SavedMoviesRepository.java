package com.mhamed.mymoviecompanion.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mhamed.mymoviecompanion.dao.SavedMoviesDAO;
import com.mhamed.mymoviecompanion.database.ConfigDatabase;
import com.mhamed.mymoviecompanion.entity.SavedMovie;

import java.util.List;

public class SavedMoviesRepository {

    private ConfigDatabase database;
    private SavedMoviesDAO savedMoviesDAO;

    public SavedMoviesRepository(Application application) {
        database = ConfigDatabase.getInstance(application);
        savedMoviesDAO = database.savedMoviesDAO();
    }

    public LiveData<List<SavedMovie>> getAllSavedMoviesByUserId(Long userId) {
        return savedMoviesDAO.getAllSavedMoviesByUserId(userId);
    }

    public void insertSavedMovie(SavedMovie savedMovie) {
        new InsertFilmsEnviesAsyncTask(savedMoviesDAO).execute(savedMovie);
    }

    private static class InsertFilmsEnviesAsyncTask extends AsyncTask<SavedMovie, Void, Void> {
        private SavedMoviesDAO savedMoviesDAO;

        private InsertFilmsEnviesAsyncTask(SavedMoviesDAO savedMoviesDAO) {
            this.savedMoviesDAO = savedMoviesDAO;
        }

        @Override
        protected Void doInBackground(SavedMovie... filmsEnvies) {
            savedMoviesDAO.insertSavedMovie(filmsEnvies[0]);
            return null;
        }
    }
}
