package com.mhamed.mymoviecompanion.DAO;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mhamed.mymoviecompanion.Entity.User;

import java.util.List;


@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Query("SELECT * from User where email=(:email) and password=(:password)")
    LiveData<User> login(String email, String password);


    @Query("SELECT * from User")
    LiveData<List<User>> getAllUser();

}