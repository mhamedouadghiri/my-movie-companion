package com.mhamed.mymoviecompanion.Database;

import android.content.Context;
import android.util.Log;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mhamed.mymoviecompanion.DAO.FilmsEnviesDAO;
import com.mhamed.mymoviecompanion.DAO.FilmsVusDAO;
import com.mhamed.mymoviecompanion.DAO.UserDAO;
import com.mhamed.mymoviecompanion.Entity.FilmsEnvies;
import com.mhamed.mymoviecompanion.Entity.FilmsVus;
import com.mhamed.mymoviecompanion.Entity.User;


@Database(entities = {User.class, FilmsEnvies.class, FilmsVus.class}, version = 2,exportSchema = false)
    public abstract class ConfigDatabase  extends RoomDatabase {

        // --- SINGLETON ---
        private static ConfigDatabase INSTANCE;


    // --- DAO ---
        public abstract UserDAO UserDAO();
        public abstract FilmsVusDAO filmsVusDAO();
        public abstract FilmsEnviesDAO filmsEnviesDAO();

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
}