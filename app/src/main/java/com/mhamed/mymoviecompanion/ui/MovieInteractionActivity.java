package com.mhamed.mymoviecompanion.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.adapters.BaseMovieEntityAdapter;
import com.mhamed.mymoviecompanion.adapters.MovieEntityItemClickListener;
import com.mhamed.mymoviecompanion.entity.BaseMovieEntity;
import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.remote.api.ApiClient;
import com.mhamed.mymoviecompanion.remote.api.MovieService;
import com.mhamed.mymoviecompanion.util.BaseActivity;
import com.mhamed.mymoviecompanion.util.SimpleCallback;
import com.mhamed.mymoviecompanion.viewmodel.SavedMoviesViewModel;
import com.mhamed.mymoviecompanion.viewmodel.WatchedMoviesViewModel;

import java.util.List;

import static com.mhamed.mymoviecompanion.util.Constants.PREFERENCES_LOGIN_ID;

public class MovieInteractionActivity extends BaseActivity implements MovieEntityItemClickListener {

    private final MovieService movieService = ApiClient.getInstance();

    private Long currentUserId;

    private String type;

    private WatchedMoviesViewModel watchedMoviesViewModel;
    private SavedMoviesViewModel savedMoviesViewModel;

    private List<? extends BaseMovieEntity> movies;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_interaction);
        type = getIntent().getStringExtra("type");
        setupToolbar();

        currentUserId = PreferenceManager.getDefaultSharedPreferences(this).getLong(PREFERENCES_LOGIN_ID, -1);

        watchedMoviesViewModel = new ViewModelProvider(this).get(WatchedMoviesViewModel.class);
        watchedMoviesViewModel.init(this.getApplication());

        savedMoviesViewModel = new ViewModelProvider(this).get(SavedMoviesViewModel.class);
        savedMoviesViewModel.init(this.getApplication());


        setMovies();
        initMovies();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setMovies();
        initMovies();
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setTitle(type);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setMovies() {
        if (type.equals(getString(R.string.watched_movies))) {
            movies = watchedMoviesViewModel.getAllWatchedMoviesByUserId(currentUserId);
        } else if (type.equals(getString(R.string.watchlist))) {
            movies = savedMoviesViewModel.getAllSavedMoviesByUserId(currentUserId);
        }
    }

    private void initMovies() {
        RecyclerView recyclerView = findViewById(R.id.movie_interaction_recyclerview);
        FlexboxLayoutManager layout = new FlexboxLayoutManager(this);
        layout.setJustifyContent(JustifyContent.SPACE_AROUND);
        recyclerView.setLayoutManager(layout);
        BaseMovieEntityAdapter adapter = new BaseMovieEntityAdapter(movies, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onMovieClick(BaseMovieEntity movie, ImageView movieImageView) {
        movieService.getMovieDetails(Long.parseLong(movie.getMovieId())).enqueue((SimpleCallback<Movie>) (call, response) -> {
            if (response.isSuccessful() && response.body() != null) {
                Intent intent = new Intent(this, MovieDetailActivity.class);
                Movie apiResponse = response.body();
                intent.putExtra("movie", apiResponse);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, movieImageView, "sharedName");
                startActivity(intent, options.toBundle());
            } else {
                Log.e("MOVIE_INTERACTION_DETAILS", response.errorBody().toString());
            }
        });
    }
}
