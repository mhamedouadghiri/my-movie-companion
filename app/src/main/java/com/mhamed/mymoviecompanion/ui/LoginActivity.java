package com.mhamed.mymoviecompanion.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.mhamed.mymoviecompanion.MainActivity;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.util.BaseActivity;
import com.mhamed.mymoviecompanion.viewmodel.UserViewModel;

import static com.mhamed.mymoviecompanion.util.Constants.PREFERENCES_LOGIN_EMAIL;
import static com.mhamed.mymoviecompanion.util.Constants.PREFERENCES_LOGIN_ID;
import static com.mhamed.mymoviecompanion.util.Constants.PREFERENCES_LOGIN_LOGGED_IN;
import static com.mhamed.mymoviecompanion.util.Constants.PREFERENCES_LOGIN_USERNAME;

public class LoginActivity extends BaseActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        TextView tvSignUp = findViewById(R.id.tvSignUpLogin);
        TextView tvSignUpSecond = findViewById(R.id.tvSignUpSecond);

        tvSignUp.setOnClickListener(onClickSignUp());
        tvSignUpSecond.setOnClickListener(onClickSignUp());

        emailEditText = findViewById(R.id.LoginEmail);
        passwordEditText = findViewById(R.id.LoginPassword);
        loginButton = findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(onClickLogin());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init(this.getApplication());
    }

    private View.OnClickListener onClickSignUp() {
        return view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(intent);
        };
    }

    private View.OnClickListener onClickLogin() {
        return view -> {
            if (confirmInput()) {
                final String email = emailEditText.getText().toString();
                final String password = passwordEditText.getText().toString();
                userViewModel.login(email, password).observe(LoginActivity.this, user -> {
                    if (user != null) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                        editor.putString(PREFERENCES_LOGIN_LOGGED_IN, "yes");
                        editor.putLong(PREFERENCES_LOGIN_ID, user.getId());
                        editor.putString(PREFERENCES_LOGIN_EMAIL, user.getEmail());
                        editor.putString(PREFERENCES_LOGIN_USERNAME, user.getUsername());
                        editor.apply();

                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect username or password.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
    }

    private boolean validateEmail() {
        String emailInput = emailEditText.getText().toString().trim();
        if (emailInput.isEmpty()) {
            emailEditText.setError("Field can't be empty");
            return false;
        } else {
            emailEditText.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = passwordEditText.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            passwordEditText.setError("Field can't be empty");
            return false;
        } else {
            passwordEditText.setError(null);
            return true;
        }
    }

    public boolean confirmInput() {
        return validateEmail() && validatePassword();
    }
}
