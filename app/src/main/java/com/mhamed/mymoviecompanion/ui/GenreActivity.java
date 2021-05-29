package com.mhamed.mymoviecompanion.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.adapters.MovieAdapter;
import com.mhamed.mymoviecompanion.adapters.MovieItemClickListener;
import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.remote.paging.MovieGenreDataSourceFactory;
import com.mhamed.mymoviecompanion.util.GenreUtil;
import com.mhamed.mymoviecompanion.viewmodel.MovieGenreViewModel;

public class GenreActivity extends AppCompatActivity implements MovieItemClickListener {

    Long genreId;
    String genreName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        genreName = (String) getIntent().getExtras().get("genre");
        genreId = GenreUtil.getGenresFromAssets(this).stream().filter(genre -> genre.getName().equals(genreName)).findFirst().orElse(null).getId();
        setupToolbar();
        initMovies();
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setTitle(genreName + " Movies");
        }
    }

    private void initMovies() {
        RecyclerView recyclerView = findViewById(R.id.genre_movies_recyclerview);
        MovieGenreViewModel viewModel = new ViewModelProvider(this, new MovieGenreDataSourceFactory(genreId)).get(MovieGenreViewModel.class);
        final MovieAdapter movieAdapter = new MovieAdapter(this, this, viewModel);
        recyclerView.setAdapter(movieAdapter);
        FlexboxLayoutManager layout = new FlexboxLayoutManager(this);
        layout.setJustifyContent(JustifyContent.SPACE_AROUND);
        recyclerView.setLayoutManager(layout);
        viewModel.getPagedList().observe(this, movieAdapter::submitList);
        viewModel.getNetworkState().observe(this, movieAdapter::setNetworkState);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, movieImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }
}
