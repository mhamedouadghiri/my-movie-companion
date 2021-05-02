package com.mhamed.mymoviecompanion.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.adapters.MovieAdapter;
import com.mhamed.mymoviecompanion.adapters.MovieItemClickListener;
import com.mhamed.mymoviecompanion.adapters.SliderPagerAdapter;
import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.Resource;
import com.mhamed.mymoviecompanion.model.Slide;
import com.mhamed.mymoviecompanion.viewmodel.PopularMoviesViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ListMovie extends AppCompatActivity implements MovieItemClickListener {

    private List<Slide> lstSlides;
    private ViewPager sliderpager;
    private TabLayout indicator;
    PopularMoviesViewModel viewModel;
    private RecyclerView MoviesRV, moviesRvWeek;

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
    //for now popular movies duplicated until i implement NowPlayingPoviesViewModel
    private void iniWeekMovies() {
        viewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel.class);
        final MovieAdapter movieAdapter =
                new MovieAdapter(this, this, viewModel);
        moviesRvWeek.setAdapter(movieAdapter);
        moviesRvWeek.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        viewModel.getPagedList().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                movieAdapter.submitList(movies);
            }
        });
        viewModel.getNetworkState().observe(this, new Observer<Resource>() {
            @Override
            public void onChanged(Resource resource) {
                movieAdapter.setNetworkState(resource);
            }
        });
    }

    private void inPopularMovies() {
        viewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel.class);
        final MovieAdapter movieAdapter =
                new MovieAdapter(this, this, viewModel);
        MoviesRV.setAdapter(movieAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        viewModel.getPagedList().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                movieAdapter.submitList(movies);
            }
        });
        viewModel.getNetworkState().observe(this, new Observer<Resource>() {
            @Override
            public void onChanged(Resource resource) {
                movieAdapter.setNetworkState(resource);
            }
        });
    }

    private void iniSlider() {
        lstSlides = new ArrayList<>();
        lstSlides.add(new Slide(R.drawable.slide1, "Slide Title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide2, "Slide Title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide1, "Slide Title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide2, "Slide Title \nmore text here"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(this, lstSlides);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
        sliderpager.setAdapter(adapter);
        indicator.setupWithViewPager(sliderpager, true);
    }

    public void iniViews() {
        sliderpager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        MoviesRV = findViewById(R.id.Rv_movies);
        moviesRvWeek = findViewById(R.id.rv_movies_week);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        // here we send movie information to detail activity
        // also we ll create the transition animation between the two activity

        Intent intent = new Intent(this, MovieDetailActivity.class);
        // send movie information to deatilActivity
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getPosterImage());
        intent.putExtra("imgCover", movie.getBackdropImage());
        intent.putExtra("overview", movie.getOverview());

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ListMovie.this, movieImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }

    class SliderTimer extends TimerTask {
        @Override
        public void run() {
            ListMovie.this.runOnUiThread(() -> {
                if (sliderpager.getCurrentItem() < lstSlides.size() - 1) {
                    sliderpager.setCurrentItem(sliderpager.getCurrentItem() + 1);
                } else
                    sliderpager.setCurrentItem(0);
            });
        }
    }
}
