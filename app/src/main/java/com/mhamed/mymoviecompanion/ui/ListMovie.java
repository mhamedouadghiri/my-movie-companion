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
import java.util.TimerTask;

public class ListMovie extends AppCompatActivity implements MovieItemClickListener {

    private PopularMoviesViewModel viewModel;
    private List<Slide> slides;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView MoviesRV;
    private RecyclerView moviesRvWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_movie);
        initViews();
        initSlider();
        initPopularMovies();
//        initWeekMovies();
    }

    //for now popular movies duplicated until i implement NowPlayingPoviesViewModel
//    private void initWeekMovies() {
//        viewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel.class);
//        final MovieAdapter movieAdapter =
//                new MovieAdapter(this, this, viewModel);
//        moviesRvWeek.setAdapter(movieAdapter);
//        moviesRvWeek.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        viewModel.getPagedList().observe(this, new Observer<PagedList<Movie>>() {
//            @Override
//            public void onChanged(PagedList<Movie> movies) {
//                movieAdapter.submitList(movies);
//            }
//        });
//        viewModel.getNetworkState().observe(this, new Observer<Resource>() {
//            @Override
//            public void onChanged(Resource resource) {
//                movieAdapter.setNetworkState(resource);
//            }
//        });
//    }

    private void initPopularMovies() {
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

    private void initSlider() {
        slides = new ArrayList<>();
        slides.add(new Slide(R.drawable.slide1, "Slide Title \nmore text here"));
        slides.add(new Slide(R.drawable.slide2, "Slide Title \nmore text here"));
        slides.add(new Slide(R.drawable.slide1, "Slide Title \nmore text here"));
        slides.add(new Slide(R.drawable.slide2, "Slide Title \nmore text here"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(this, slides);
        // sliding is disturbing
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
        sliderPager.setAdapter(adapter);
        indicator.setupWithViewPager(sliderPager, true);
    }

    public void initViews() {
        sliderPager = findViewById(R.id.slider_pager);
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
        // send movie information to detailActivity
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("posterPath", movie.getPosterPath());
        intent.putExtra("backdropPath", movie.getBackdropPath());
//        intent.putExtra("imgURL", movie.getPosterImage());
//        intent.putExtra("imgCover", movie.getBackdropImage());
        intent.putExtra("overview", movie.getOverview());

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
