package com.mhamed.mymoviecompanion;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.mhamed.mymoviecompanion.movieApi.Controller;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.tag("TMDB_API_KEY").i(BuildConfig.TMDB_API_KEY);
        Controller controller = new Controller();
        controller.start();
    }
}
