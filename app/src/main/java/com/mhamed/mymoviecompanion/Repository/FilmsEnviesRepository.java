package com.mhamed.mymoviecompanion.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mhamed.mymoviecompanion.DAO.FilmsEnviesDAO;
import com.mhamed.mymoviecompanion.Database.ConfigDatabase;
import com.mhamed.mymoviecompanion.Entity.FilmsEnvies;

import java.util.List;


public class FilmsEnviesRepository {
    private FilmsEnviesDAO filmsEnviesDAO;
    ConfigDatabase database;


    public FilmsEnviesRepository(Application application){
        database = ConfigDatabase.getInstance(application);
        filmsEnviesDAO=database.filmsEnviesDAO();

    }

    public LiveData<List<FilmsEnvies>> getAllFilmsEnviesUser(int id_user){
        return filmsEnviesDAO.getAllFilmsEnviesUser(id_user);
    }

    public void InsertFilmsVus(FilmsEnvies filmsEnvies){
        new InsertFilmsEnviesAsyncTask(filmsEnviesDAO).execute(filmsEnvies);
    }

    private static class  InsertFilmsEnviesAsyncTask extends AsyncTask<FilmsEnvies,Void,Void> {
        private FilmsEnviesDAO filmsEnviesDAO;

        private InsertFilmsEnviesAsyncTask(FilmsEnviesDAO filmsEnviesDAO){
            this.filmsEnviesDAO = filmsEnviesDAO;
        }
        @Override
        protected Void doInBackground(FilmsEnvies... filmsEnvies) {
            filmsEnviesDAO.insertFilmsEnvies(filmsEnvies[0]);
            return  null;
        }
    }
}
