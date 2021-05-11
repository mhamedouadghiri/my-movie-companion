package com.mhamed.mymoviecompanion.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mhamed.mymoviecompanion.Entity.User;
import com.mhamed.mymoviecompanion.MainActivity;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.viewmodel.UserViewModel;


public class LoginActivity extends AppCompatActivity {

    private EditText email ;
    private EditText password ;
    private Button login;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        TextView tvSignUp = findViewById(R.id.tvSignUpLogin);
        TextView tvSignUpSecond = findViewById(R.id.tvSignUpSecond);
        tvSignUp.setOnClickListener(onClickLogin());
        tvSignUpSecond.setOnClickListener(onClickLogin());
        email = findViewById(R.id.LoginEmail);
        password = findViewById(R.id.LoginPassword);
        login = findViewById(R.id.LoginButton);
        login.setOnClickListener(onClickSignUp());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init(this.getApplication());
    }

    private View.OnClickListener onClickLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickSignUp() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confirmInput()){
                    final String Email = email.getText().toString();
                    final String Password = password.getText().toString();
                    userViewModel.login( Email,Password).observe(LoginActivity.this , new Observer<User>() {
                       @Override
                       public void onChanged(User user) {
                           if(user!=null){
                               Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                               intent.putExtra("User",user);
                               LoginActivity.this.startActivity(intent);
                           }else{
                               Toast.makeText(getApplicationContext(),"invalided email or password" , Toast.LENGTH_SHORT).show();
                           }
                       }
                    });
                }
            }
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
        if (!validateEmail() |  !validatePassword()) {
            return false;
        }
        return true;
    }
}