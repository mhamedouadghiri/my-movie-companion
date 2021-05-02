package com.mhamed.mymoviecompanion.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.model.Resource;
import com.mhamed.mymoviecompanion.viewmodel.PopularMoviesViewModel;

public class NetworkStateViewHolder extends RecyclerView.ViewHolder {
    private TextView error;
    private Button button;
    private ProgressBar progressBar;


    public NetworkStateViewHolder(@NonNull View itemView, final PopularMoviesViewModel viewModel) {
        super(itemView);
        error = itemView.findViewById(R.id.error_msg);
        button = itemView.findViewById(R.id.retry_button);
        progressBar = itemView.findViewById(R.id.progress_bar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.retry();
            }
        });
    }


    public void bindTo(Resource resource) {
        if (resource != null) {
            if (resource.message == null || resource.message.isEmpty()) {
                error.setText("Unknown Error");
            } else error.setText(resource.message);
            error.setVisibility(resource.status == Resource.Status.ERROR ? (int) 1 : (int) 0);
            progressBar.setVisibility(resource.status == Resource.Status.LOADING ? (int) 1 : (int) 0);
            button.setVisibility(resource.status == Resource.Status.ERROR ? (int) 1 : (int) 0);
        }
    }
}