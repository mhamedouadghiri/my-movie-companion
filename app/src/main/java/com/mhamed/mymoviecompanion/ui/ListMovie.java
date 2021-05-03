package com.mhamed.mymoviecompanion.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.adapters.MovieAdapter;
import com.mhamed.mymoviecompanion.adapters.MovieItemClickListener;
import com.mhamed.mymoviecompanion.adapters.SliderPagerAdapter;
import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.Slide;
import com.mhamed.mymoviecompanion.viewmodel.BaseViewModel;
import com.mhamed.mymoviecompanion.viewmodel.NowPlayingMoviesViewModel;
import com.mhamed.mymoviecompanion.viewmodel.PopularMoviesViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ListMovie extends AppCompatActivity implements MovieItemClickListener {

    private List<Slide> slides;
    private ViewPager sliderPager;
    private TabLayout indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_movie);
        initViews();
        initSlider();
        initMovies(R.id.popular_movies_recyclerview, PopularMoviesViewModel.class);
        initMovies(R.id.now_playing_movies_recyvlerview, NowPlayingMoviesViewModel.class);
    }

    public void initViews() {
        sliderPager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
    }

    private void initMovies(int recyclerViewId, Class<? extends BaseViewModel> clazz) {
        RecyclerView recyclerView = findViewById(recyclerViewId);
        BaseViewModel viewModel = ViewModelProviders.of(this).get(clazz);
        final MovieAdapter movieAdapter = new MovieAdapter(this, this, viewModel);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        viewModel.getPagedList().observe(this, movieAdapter::submitList);
        viewModel.getNetworkState().observe(this, movieAdapter::setNetworkState);
    }

    private void initSlider() {
        slides = new ArrayList<>();
        slides.add(new Slide(R.drawable.slide1, "Slide Title"));
        slides.add(new Slide(R.drawable.slide2, "Slide Title"));
        slides.add(new Slide(R.drawable.slide1, "Slide Title"));
        slides.add(new Slide(R.drawable.slide2, "Slide Title"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(this, slides);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
        sliderPager.setAdapter(adapter);
        indicator.setupWithViewPager(sliderPager, true);
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        // here we send movie information to detail activity
        // and we create the transition animation between the two activities

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie", movie);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ListMovie.this, movieImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }

    class SliderTimer extends TimerTask {
        @Override
        public void run() {
            ListMovie.this.runOnUiThread(() -> {
                if (sliderPager.getCurrentItem() < slides.size() - 1) {
                    sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
                } else
                    sliderPager.setCurrentItem(0);
            });
        }
    }
}
