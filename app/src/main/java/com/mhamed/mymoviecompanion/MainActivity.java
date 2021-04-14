package com.mhamed.mymoviecompanion;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mhamed.mymoviecompanion.recommender.TestRecSys;
import com.mhamed.mymoviecompanion.movieApi.ApiAsync;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, TestRecSys.class));

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.tag("TMDB_API_KEY").i(BuildConfig.TMDB_API_KEY);
        ApiAsync apiAsync = new ApiAsync();
        apiAsync.start((long) 3);
    }
}
