package com.mhamed.mymoviecompanion.Template.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.Template.adapters.MovieAdapter;
import com.mhamed.mymoviecompanion.Template.adapters.MovieItemClickListener;
import com.mhamed.mymoviecompanion.Template.adapters.SliderPagerAdapter;
import com.mhamed.mymoviecompanion.Template.models.Movie;
import com.mhamed.mymoviecompanion.Template.models.Slide;
import com.mhamed.mymoviecompanion.Template.utils.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ListeMovie extends AppCompatActivity implements MovieItemClickListener {
    private List<Slide> lstSlides ;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView MoviesRV , moviesRvWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_movie);
        iniViews();

        // prepare a list of slides ..
        iniSlider();

        inPopularMovies();
        iniWeekMovies();

    }

    private void iniWeekMovies() {
        MovieAdapter weekMoviesAdapter = new MovieAdapter(this, DataSource.getWeekMovies(),this);
        moviesRvWeek.setAdapter(weekMoviesAdapter);
        moviesRvWeek.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
    }


    private void inPopularMovies() {
       MovieAdapter movieAdapter = new MovieAdapter(this, DataSource.getPopularMovies(), this);
        MoviesRV.setAdapter(movieAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
    }

    private void iniSlider() {
        lstSlides = new ArrayList<>() ;
        lstSlides.add(new Slide(R.drawable.slide1,"Slide Title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide2,"Slide Title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide1,"Slide Title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide2,"Slide Title \nmore text here"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(this,lstSlides);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),4000,6000);
        sliderpager.setAdapter(adapter);
        indicator.setupWithViewPager(sliderpager,true);
    }

    public void iniViews(){
        sliderpager = findViewById(R.id.slider_pager) ;
        indicator = findViewById(R.id.indicator);
        MoviesRV = findViewById(R.id.Rv_movies);
        moviesRvWeek=findViewById(R.id.rv_movies_week);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        // here we send movie information to detail activity
        // also we ll create the transition animation between the two activity

        Intent intent = new Intent(this, MovieDetailActivity.class);
        // send movie information to deatilActivity
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());
        // lets crezte the animation

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ListeMovie.this,
                movieImageView,"sharedName");
        startActivity(intent,options.toBundle());

    }


    class SliderTimer extends TimerTask {
        @Override
        public void run() {
            ListeMovie.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem()<lstSlides.size()-1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else
                        sliderpager.setCurrentItem(0);
                }
            });
        }
    }


}