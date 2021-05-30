package com.mhamed.mymoviecompanion.util;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.mhamed.mymoviecompanion.ui.LoginActivity;
import com.mhamed.mymoviecompanion.ui.RegisterActivity;

import static com.mhamed.mymoviecompanion.util.Constants.PREFERENCES_LOGIN_EMAIL;
import static com.mhamed.mymoviecompanion.util.Constants.PREFERENCES_LOGIN_ID;
import static com.mhamed.mymoviecompanion.util.Constants.PREFERENCES_LOGIN_LOGGED_IN;
import static com.mhamed.mymoviecompanion.util.Constants.PREFERENCES_LOGIN_USERNAME;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (sharedPreferences.getString(PREFERENCES_LOGIN_LOGGED_IN, "no").equals("no"))
            if (!(this instanceof LoginActivity) && !(this instanceof RegisterActivity)) {
                showLoginActivity();
            }
    }

    private void showLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void logout() {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
        editor.putString(PREFERENCES_LOGIN_LOGGED_IN, "no");
        editor.remove(PREFERENCES_LOGIN_ID);
        editor.remove(PREFERENCES_LOGIN_EMAIL);
        editor.remove(PREFERENCES_LOGIN_USERNAME);
        editor.apply();

        showLoginActivity();
    }
}
