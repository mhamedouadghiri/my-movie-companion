package com.mhamed.mymoviecompanion.viewmodel;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mhamed.mymoviecompanion.Entity.User;
import com.mhamed.mymoviecompanion.Repository.UserRepository;

import java.util.List;


public class UserViewModel extends ViewModel {
    private UserRepository userRepository;
    private LiveData<List<User>> AllUsers;

    public void init(Application application){
        userRepository = new UserRepository(application);
        AllUsers = userRepository.getAllUsers();
    }


    public void insertUser(User user){
         userRepository.InsertUser(user);
    }


    public LiveData<User> login(String email , String password){
        return userRepository.login(email,password);
    }

    public LiveData<List<User>> getAllUsers(){
        if (AllUsers == null){
            AllUsers = new MutableLiveData<>();
        }
        return AllUsers;
    }
}
