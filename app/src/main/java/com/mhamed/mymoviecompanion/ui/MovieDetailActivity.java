package com.mhamed.mymoviecompanion.ui;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.adapters.CastAdapter;
import com.mhamed.mymoviecompanion.model.Cast;
import com.mhamed.mymoviecompanion.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView movieThumbnailImg, movieCoverImg;
    private TextView tv_title, tv_description;
    private FloatingActionButton play_fab;
    private RecyclerView RvCast;
    private CastAdapter castAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        iniViews();
        setupRvCast();
    }

    void iniViews() {
        RvCast = findViewById(R.id.rv_cast);
        play_fab = findViewById(R.id.play_fab);
        String movieTitle = getIntent().getExtras().getString("title");
//        int imageResourceId = getIntent().getExtras().getInt("imgURL");
//        int imagecover = getIntent().getExtras().getInt("imgCover");
        String overview = getIntent().getExtras().getString("overview");
        movieThumbnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(Constants.BASE_IMAGE_URL + getIntent().getExtras().getString("posterPath")).into(movieThumbnailImg);
//        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
//        MovieThumbnailImg.setImageResource(imageResourceId);
        movieCoverImg = findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(Constants.BASE_IMAGE_URL + getIntent().getExtras().getString("backdropPath")).into(movieCoverImg);
//        Glide.with(this).load(imagecover).into(MovieCoverImg);
        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        //getSupportActionBar().setTitle(movieTitle);
        tv_description = findViewById(R.id.detail_movie_desc);
        tv_description.setText(overview);
        // setup animation
        movieCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
    }

    void setupRvCast() {
        List<Cast> mdata = new ArrayList<>();
        mdata.add(new Cast("name", R.drawable.moana));
        mdata.add(new Cast("name", R.drawable.moana));
        mdata.add(new Cast("name", R.drawable.moana));
        mdata.add(new Cast("name", R.drawable.moana));
        mdata.add(new Cast("name", R.drawable.moana));
        mdata.add(new Cast("name", R.drawable.moana));
        castAdapter = new CastAdapter(this, mdata);
        RvCast.setAdapter(castAdapter);
        RvCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
}
