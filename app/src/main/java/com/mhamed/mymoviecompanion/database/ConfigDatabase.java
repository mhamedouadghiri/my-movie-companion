package com.mhamed.mymoviecompanion.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mhamed.mymoviecompanion.dao.SavedMoviesDAO;
import com.mhamed.mymoviecompanion.dao.UserDAO;
import com.mhamed.mymoviecompanion.dao.WatchedMoviesDAO;
import com.mhamed.mymoviecompanion.entity.SavedMovies;
import com.mhamed.mymoviecompanion.entity.User;
import com.mhamed.mymoviecompanion.entity.WatchedMovies;

@Database(entities = {User.class, SavedMovies.class, WatchedMovies.class}, version = 3, exportSchema = false)
public abstract class ConfigDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static ConfigDatabase INSTANCE;

    // --- INSTANCE ---
    public static synchronized ConfigDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ConfigDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ConfigDatabase.class, "films.db").fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // --- DAO ---
    public abstract UserDAO userDAO();

    public abstract WatchedMoviesDAO watchedMoviesDAO();

    public abstract SavedMoviesDAO savedMoviesDAO();
}
