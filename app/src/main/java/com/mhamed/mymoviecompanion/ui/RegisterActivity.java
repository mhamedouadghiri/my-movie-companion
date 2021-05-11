package com.mhamed.mymoviecompanion.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mhamed.mymoviecompanion.Entity.User;
import com.mhamed.mymoviecompanion.MainActivity;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.viewmodel.UserViewModel;


public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText username;
    private UserViewModel userViewModel;
    private Button register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        TextView tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(onLoginListner());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init(this.getApplication());
        email=findViewById(R.id.email);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        register = findViewById(R.id.register);
        register.setOnClickListener(onRegisterListner());
    }

    private View.OnClickListener onLoginListner(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                RegisterActivity.this.startActivity(intent);
            }
        };
    }

    private View.OnClickListener onRegisterListner(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confirmInput()){
                    final User user = new User();
                    user.setEmail(email.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setUsername(username.getText().toString());
                    userViewModel.insertUser(user);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.this.startActivity(intent);
                }
            };
        };

    }

    private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();
        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }
    private boolean validateUsername() {
        String usernameInput = username.getText().toString().trim();
        if (usernameInput.isEmpty()) {
            username.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            username.setError("Username too long");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public boolean confirmInput() {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return false;
        }
        return true;
    }


}