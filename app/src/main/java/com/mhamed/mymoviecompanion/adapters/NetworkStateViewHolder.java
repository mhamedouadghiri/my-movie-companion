package com.mhamed.mymoviecompanion.adapters;

import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.model.Resource;

public class NetworkStateViewHolder extends RecyclerView.ViewHolder {
    private final ProgressBar progressBar;

    public NetworkStateViewHolder(@NonNull View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
    }

    public void bindTo(Resource resource) {
        if (resource != null) {
            progressBar.setVisibility(resource.status == Resource.Status.LOADING ? (int) 1 : (int) 0);
        }
    }
}
