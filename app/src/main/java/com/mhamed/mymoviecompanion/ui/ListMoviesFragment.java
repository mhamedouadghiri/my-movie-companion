package com.mhamed.mymoviecompanion.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.adapters.MovieAdapter;
import com.mhamed.mymoviecompanion.adapters.MovieItemClickListener;
import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.MoviesFilterType;
import com.mhamed.mymoviecompanion.viewmodel.BaseViewModel;
import com.mhamed.mymoviecompanion.viewmodel.NowPlayingMoviesViewModel;
import com.mhamed.mymoviecompanion.viewmodel.PopularMoviesViewModel;
import com.mhamed.mymoviecompanion.viewmodel.TopRatedMoviesViewModel;

public class ListMoviesFragment extends Fragment implements MovieItemClickListener {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_movies, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Spinner spinner = view.findViewById(R.id.movies_filter_type_spinner);
        ArrayAdapter<MoviesFilterType> adapter = new ArrayAdapter<>(requireContext(), R.layout.support_simple_spinner_dropdown_item, MoviesFilterType.values());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Class<? extends BaseViewModel> clazz;
                switch (((MoviesFilterType) parent.getItemAtPosition(position))) {
                    case TOP_RATED:
                        clazz = TopRatedMoviesViewModel.class;
                        break;
                    case NOW_PLAYING:
                        clazz = NowPlayingMoviesViewModel.class;
                        break;
                    case POPULAR:
                    default:
                        clazz = PopularMoviesViewModel.class;
                }
                initMovies(clazz);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initMovies(Class<? extends BaseViewModel> clazz) {
        RecyclerView recyclerView = requireView().findViewById(R.id.list_movies_recyclerview);
        BaseViewModel viewModel = ViewModelProviders.of(this).get(clazz);
        final MovieAdapter movieAdapter = new MovieAdapter(getContext(), this, viewModel);
        recyclerView.setAdapter(movieAdapter);
        FlexboxLayoutManager layout = new FlexboxLayoutManager(getContext());
        layout.setJustifyContent(JustifyContent.SPACE_AROUND);
        recyclerView.setLayoutManager(layout);
        viewModel.getPagedList().observe(getViewLifecycleOwner(), movieAdapter::submitList);
        viewModel.getNetworkState().observe(getViewLifecycleOwner(), movieAdapter::setNetworkState);
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), movieImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }
}
