package com.mhamed.mymoviecompanion.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class ListMoviesFragment extends Fragment implements MovieItemClickListener {

    private View view;
    private List<Slide> slides;
    private ViewPager sliderPager;
    private TabLayout indicator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_movies, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        sliderPager = requireView().findViewById(R.id.slider_pager);
        indicator = requireView().findViewById(R.id.indicator);
        initSlider();
        initMovies(R.id.popular_movies_recyclerview, PopularMoviesViewModel.class);
        initMovies(R.id.now_playing_movies_recyvlerview, NowPlayingMoviesViewModel.class);
    }

    private void initMovies(int recyclerViewId, Class<? extends BaseViewModel> clazz) {
        RecyclerView recyclerView = requireView().findViewById(recyclerViewId);
        BaseViewModel viewModel = ViewModelProviders.of(this).get(clazz);
        final MovieAdapter movieAdapter = new MovieAdapter(getContext(), this, viewModel);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        viewModel.getPagedList().observe(getViewLifecycleOwner(), movieAdapter::submitList);
        viewModel.getNetworkState().observe(getViewLifecycleOwner(), movieAdapter::setNetworkState);
    }

    private void initSlider() {
        slides = new ArrayList<>();
        slides.add(new Slide(R.drawable.slide1, "Slide Title"));
        slides.add(new Slide(R.drawable.slide2, "Slide Title"));
        slides.add(new Slide(R.drawable.slide1, "Slide Title"));
        slides.add(new Slide(R.drawable.slide2, "Slide Title"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(getContext(), slides);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new ListMoviesFragment.SliderTimer(), 4000, 6000);
        sliderPager.setAdapter(adapter);
        indicator.setupWithViewPager(sliderPager, true);
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        // here we send movie information to detail activity
        // and we create the transition animation between the two activities

        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("movie", movie);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), movieImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }

    class SliderTimer extends TimerTask {
        @Override
        public void run() {
            if (getActivity() == null) {
                return;
            }
            getActivity().runOnUiThread(() -> {
                if (sliderPager.getCurrentItem() < slides.size() - 1) {
                    sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
                } else {
                    sliderPager.setCurrentItem(0);
                }
            });
        }
    }
}