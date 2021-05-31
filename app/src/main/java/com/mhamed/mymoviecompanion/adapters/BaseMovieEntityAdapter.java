package com.mhamed.mymoviecompanion.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.databinding.ItemBaseEntityMovieBinding;
import com.mhamed.mymoviecompanion.entity.BaseMovieEntity;

import java.util.List;

public class BaseMovieEntityAdapter extends RecyclerView.Adapter<BaseMovieEntityAdapter.MyViewHolder> {

    private final List<? extends BaseMovieEntity> movies;
    private final MovieEntityItemClickListener movieItemClickListener;

    public BaseMovieEntityAdapter(List<? extends BaseMovieEntity> movies, MovieEntityItemClickListener listener) {
        this.movies = movies;
        movieItemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(ItemBaseEntityMovieBinding.inflate(layoutInflater, parent, false), movieItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindTo(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemBaseEntityMovieBinding binding;
        private final MovieEntityItemClickListener movieItemClickListener;

        public MyViewHolder(@NonNull ItemBaseEntityMovieBinding binding, MovieEntityItemClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            movieItemClickListener = listener;
        }

        public void bindTo(BaseMovieEntity movie) {
            binding.setMovie(movie);
            itemView.setOnClickListener(v -> movieItemClickListener.onMovieClick(movie, itemView.findViewById(R.id.movie_poster)));
        }
    }
}
