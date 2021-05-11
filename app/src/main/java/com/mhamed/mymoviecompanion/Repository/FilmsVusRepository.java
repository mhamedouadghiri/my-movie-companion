package com.mhamed.mymoviecompanion.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.mhamed.mymoviecompanion.DAO.FilmsVusDAO;
import com.mhamed.mymoviecompanion.Database.ConfigDatabase;
import com.mhamed.mymoviecompanion.Entity.FilmsVus;

import java.util.List;


public class FilmsVusRepository {
    private FilmsVusDAO filmsVusDAO;
    ConfigDatabase database;


    public FilmsVusRepository(Application application){
        database = ConfigDatabase.getInstance(application);
        filmsVusDAO=database.filmsVusDAO();

    }

    public LiveData<List<FilmsVus>> getAllFilmsVusUser(int id_user){
        return filmsVusDAO.getAllFilmsVusUser(id_user);
    }

    public void InsertFilmsVus(FilmsVus filmsVus){
        new InsertFilmsVusAsyncTask(filmsVusDAO).execute(filmsVus);
    }

    private static class  InsertFilmsVusAsyncTask extends AsyncTask<FilmsVus,Void,Void> {
        private FilmsVusDAO filmsVusDAO;
        private InsertFilmsVusAsyncTask(FilmsVusDAO filmsVusDAO){
            this.filmsVusDAO = filmsVusDAO;
        }
        @Override
        protected Void doInBackground(FilmsVus... filmsVus) {
            filmsVusDAO.insertFilmsVus(filmsVus[0]);
            return  null;
        }
    }
}
