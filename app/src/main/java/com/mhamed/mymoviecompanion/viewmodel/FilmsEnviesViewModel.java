package com.mhamed.mymoviecompanion.viewmodel;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.mhamed.mymoviecompanion.Entity.FilmsEnvies;
import com.mhamed.mymoviecompanion.Repository.FilmsEnviesRepository;

import java.util.List;


public class FilmsEnviesViewModel extends ViewModel {
    private FilmsEnviesRepository filmsEnviesRepository;


    public void init(Application application){
        filmsEnviesRepository = new FilmsEnviesRepository(application);
    }


    public void insertFilmsEnvies(FilmsEnvies filmsEnvies){
        filmsEnviesRepository.InsertFilmsVus(filmsEnvies);
    }

    public LiveData<List<FilmsEnvies>> getAllFilmsEnviesUser(int id_user){
        return filmsEnviesRepository.getAllFilmsEnviesUser(id_user);
    }
}
