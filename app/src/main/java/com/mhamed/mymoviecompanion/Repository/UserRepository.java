package com.mhamed.mymoviecompanion.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;


import androidx.lifecycle.LiveData;

import com.mhamed.mymoviecompanion.DAO.UserDAO;
import com.mhamed.mymoviecompanion.Database.ConfigDatabase;
import com.mhamed.mymoviecompanion.Entity.User;

import java.util.List;


public class UserRepository {
    private UserDAO userDAO;
    private LiveData<List<User>> AllUsers;
    ConfigDatabase database;

    public UserRepository(Application application){
        database = ConfigDatabase.getInstance(application);
        userDAO=database.UserDAO();
        AllUsers = userDAO.getAllUser();
    }

    public void InsertUser(User user){
        new InsertUserAsyncTask(userDAO).execute(user);
    }

    public LiveData<List<User>> getAllUsers(){
        return  AllUsers;
    }

    public LiveData<User> login(String email, String password){
            return userDAO.login(email,password);
    }

    private static class  InsertUserAsyncTask extends AsyncTask<User,Void,Void> {
        private UserDAO userDAO;

        private InsertUserAsyncTask(UserDAO userDAO){
            this.userDAO = userDAO;
        }
        @Override
        protected Void doInBackground(User... users) {
             userDAO.insertUser(users[0]);
             return  null;
        }
    }


}
