package com.mhamed.mymoviecompanion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.databinding.ItemMovieBinding;
import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.Resource;
import com.mhamed.mymoviecompanion.viewmodel.BaseViewModel;

public class MovieAdapter extends PagedListAdapter<Movie, RecyclerView.ViewHolder> {

    private static final DiffUtil.ItemCallback<Movie> MOVIE_COMPARATOR = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };

    private final Context context;
    private final MovieItemClickListener movieItemClickListener;
    private final BaseViewModel viewModel;
    private Resource resource = null;

    public MovieAdapter(Context context, MovieItemClickListener listener, BaseViewModel viewModel) {
        super(MOVIE_COMPARATOR);
        this.context = context;
        this.viewModel = viewModel;
        movieItemClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == R.layout.item_movie) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            return new MyViewHolder(ItemMovieBinding.inflate(layoutInflater, parent, false));
        } else if (viewType == R.layout.item_network_state) {
            View nview = LayoutInflater.from(context).inflate(R.layout.item_network_state, parent, false);
            return new NetworkStateViewHolder(nview, viewModel);
        }
        throw new IllegalArgumentException("unknown view type " + viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == R.layout.item_movie) {
            ((MyViewHolder) holder).bindTo(getItem(position));
        } else if (itemViewType == R.layout.item_network_state) {
            ((NetworkStateViewHolder) holder).bindTo(resource);
        } else {
            throw new IllegalArgumentException("unknown view type");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.item_network_state;
        } else {
            return R.layout.item_movie;
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + (hasExtraRow() ? 1 : 0);
    }

    private boolean hasExtraRow() {
        return resource != null && resource.status != Resource.Status.SUCCESS;
    }

    public void setNetworkState(Resource resource) {
        Resource previousState = this.resource;
        boolean hadExtraRow = hasExtraRow();
        this.resource = resource;
        boolean hasExtraRow = hasExtraRow();
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount());
            } else {
                notifyItemInserted(super.getItemCount());
            }
        } else if (hasExtraRow && !previousState.equals(resource)) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding binding;

        public MyViewHolder(@NonNull ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(Movie movie) {
            binding.setMovie(movie);
            itemView.setOnClickListener(v -> movieItemClickListener.onMovieClick(movie, itemView.findViewById(R.id.item_movie_img)));
        }
    }
}