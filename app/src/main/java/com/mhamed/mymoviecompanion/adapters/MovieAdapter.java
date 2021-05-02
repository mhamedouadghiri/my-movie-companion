package com.mhamed.mymoviecompanion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.Resource;
import com.mhamed.mymoviecompanion.viewmodel.PopularMoviesViewModel;

public class MovieAdapter extends PagedListAdapter<Movie, RecyclerView.ViewHolder> {

    private Context context;
    private Resource resource = null;
    private MovieItemClickListener movieItemClickListener;
    private PopularMoviesViewModel viewModel;


    public MovieAdapter(Context context, MovieItemClickListener listener, PopularMoviesViewModel viewModel) {
        super(MOVIE_COMPARATOR);
        this.context = context;
        this.viewModel = viewModel;
        movieItemClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case R.layout.item_movie:
                View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
                return new MyViewHolder(view);
            case R.layout.item_network_state:
                View nview = LayoutInflater.from(context).inflate(R.layout.item_network_state, parent, false);
                return new NetworkStateViewHolder(nview, viewModel);
            default:
                throw new IllegalArgumentException("unknown view type " + viewType);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.item_movie:
                ((MyViewHolder) holder).bindTo(getItem(position));
                break;
            case R.layout.item_network_state:
                ((NetworkStateViewHolder) holder).bindTo(resource);
                break;
            default:
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView TvTitle;
        private ImageView ImgMovie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TvTitle = itemView.findViewById(R.id.item_movie_title);
            ImgMovie = itemView.findViewById(R.id.item_movie_img);
            itemView.setOnClickListener(v -> movieItemClickListener.onMovieClick(getItem(getAdapterPosition()), ImgMovie));
        }

        public void bindTo(Movie movie) {
            this.ImgMovie.setImageResource(movie.getPosterImage());
            this.TvTitle.setText(movie.getTitle());
        }
    }

    private static DiffUtil.ItemCallback<Movie> MOVIE_COMPARATOR = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };
}
