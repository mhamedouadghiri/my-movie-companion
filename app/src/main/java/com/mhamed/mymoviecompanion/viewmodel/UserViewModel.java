package com.mhamed.mymoviecompanion.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mhamed.mymoviecompanion.entity.User;
import com.mhamed.mymoviecompanion.repository.UserRepository;

import java.util.List;


public class UserViewModel extends ViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> allUsers;

    public void init(Application application) {
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    public LiveData<User> login(String email, String password) {
        return userRepository.login(email, password);
    }

    public LiveData<List<User>> getAllUsers() {
        if (allUsers == null) {
            allUsers = new MutableLiveData<>();
        }
        return allUsers;
    }
}
