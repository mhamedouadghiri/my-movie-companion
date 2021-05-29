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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.adapters.MovieAdapter;
import com.mhamed.mymoviecompanion.adapters.MovieItemClickListener;
import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.remote.paging.MovieSearchResultsDataSourceFactory;
import com.mhamed.mymoviecompanion.viewmodel.SearchResultsViewModel;

public class DisplaySearchResultsFragment extends Fragment implements MovieItemClickListener {

    private final String query;

    private View view;

    public DisplaySearchResultsFragment(String query) {
        this.query = query;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_display_search_results, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initMovies();
    }

    private void initMovies() {
        RecyclerView recyclerView = requireView().findViewById(R.id.search_results_recyclerview);
        SearchResultsViewModel viewModel = new ViewModelProvider(this, new MovieSearchResultsDataSourceFactory(query)).get(SearchResultsViewModel.class);
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
