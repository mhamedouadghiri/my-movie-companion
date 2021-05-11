package com.mhamed.mymoviecompanion.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mhamed.mymoviecompanion.dao.UserDAO;
import com.mhamed.mymoviecompanion.database.ConfigDatabase;
import com.mhamed.mymoviecompanion.entity.User;

import java.util.List;

public class UserRepository {

    private ConfigDatabase database;
    private UserDAO userDAO;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        database = ConfigDatabase.getInstance(application);
        userDAO = database.userDAO();
        allUsers = userDAO.getAllUser();
    }

    public void insertUser(User user) {
        new InsertUserAsyncTask(userDAO).execute(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<User> login(String email, String password) {
        return userDAO.login(email, password);
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private InsertUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.insertUser(users[0]);
            return null;
        }
    }
}
