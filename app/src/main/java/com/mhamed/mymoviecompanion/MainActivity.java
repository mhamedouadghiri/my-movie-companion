package com.mhamed.mymoviecompanion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.mhamed.mymoviecompanion.recommender.TestRecSys;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("TMDB_API_KEY", BuildConfig.TMDB_API_KEY);

        startActivity(new Intent(this, TestRecSys.class));
    }
}
