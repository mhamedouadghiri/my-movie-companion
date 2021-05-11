package com.mhamed.mymoviecompanion.DAO;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mhamed.mymoviecompanion.Entity.FilmsVus;

import java.util.List;


@Dao
public interface FilmsVusDAO {
    @Insert
    void insertFilmsVus(FilmsVus filmsVus);

    @Query("SELECT * From FilmsVus where id_user=(:id_user) ")
    LiveData<List<FilmsVus>> getAllFilmsVusUser(int id_user);
}
