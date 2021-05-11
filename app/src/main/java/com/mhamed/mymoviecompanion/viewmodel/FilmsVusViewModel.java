package com.mhamed.mymoviecompanion.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mhamed.mymoviecompanion.Entity.FilmsVus;
import com.mhamed.mymoviecompanion.Repository.FilmsVusRepository;

import java.util.List;


public class FilmsVusViewModel extends ViewModel {
    private FilmsVusRepository filmsVusRepository;


    public void init(Application application){
        filmsVusRepository = new FilmsVusRepository(application);
    }


    public void insertFilmsVus(FilmsVus filmsVus){

    Log.i("FilmsModel",filmsVus.getCritique());
    filmsVusRepository.InsertFilmsVus(filmsVus);
    }

    public LiveData<List<FilmsVus>> getAllFilmsVusUser(int id_user){
        return filmsVusRepository.getAllFilmsVusUser(id_user);
    }
}
