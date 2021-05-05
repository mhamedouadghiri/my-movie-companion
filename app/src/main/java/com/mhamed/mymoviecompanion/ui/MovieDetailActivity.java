package com.mhamed.mymoviecompanion.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.adapters.CastAdapter;
import com.mhamed.mymoviecompanion.databinding.ActivityMovieDetailBinding;
import com.mhamed.mymoviecompanion.model.Cast;
import com.mhamed.mymoviecompanion.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private ActivityMovieDetailBinding binding;

    private RecyclerView castRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        Movie movie = (Movie) getIntent().getExtras().get("movie");
        binding.setMovie(movie);

        castRecyclerView = findViewById(R.id.rv_cast);

        // what is this for ?
        FloatingActionButton playFAB = findViewById(R.id.play_fab);
        playFAB.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        ImageView movieCoverImg = findViewById(R.id.detail_movie_cover);
        movieCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));

        setCastRecyclerView();
    }

    void setCastRecyclerView() {
        List<Cast> mdata = new ArrayList<>();
        mdata.add(new Cast("name", R.drawable.moana));
        mdata.add(new Cast("name", R.drawable.moana));
        mdata.add(new Cast("name", R.drawable.moana));
        mdata.add(new Cast("name", R.drawable.moana));
        mdata.add(new Cast("name", R.drawable.moana));
        mdata.add(new Cast("name", R.drawable.moana));
        CastAdapter castAdapter = new CastAdapter(this, mdata);
        castRecyclerView.setAdapter(castAdapter);
        castRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }


}
