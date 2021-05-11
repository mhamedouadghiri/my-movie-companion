package com.mhamed.mymoviecompanion.DAO;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mhamed.mymoviecompanion.Entity.FilmsEnvies;

import java.util.List;

@Dao
public interface FilmsEnviesDAO {
   @Insert
   void insertFilmsEnvies(FilmsEnvies filmsEnvies);

   @Query("SELECT * From FilmsEnvies where id_user=(:id_user) ")
   LiveData<List<FilmsEnvies>> getAllFilmsEnviesUser(int id_user);
}
