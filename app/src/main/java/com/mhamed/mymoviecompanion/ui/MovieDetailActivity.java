package com.mhamed.mymoviecompanion.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.adapters.CastAdapter;
import com.mhamed.mymoviecompanion.databinding.ActivityMovieDetailBinding;
import com.mhamed.mymoviecompanion.model.Cast;
import com.mhamed.mymoviecompanion.model.CreditsResponse;
import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.Trailer;
import com.mhamed.mymoviecompanion.model.TrailersResponse;
import com.mhamed.mymoviecompanion.remote.api.ApiClient;
import com.mhamed.mymoviecompanion.remote.api.MovieService;
import com.mhamed.mymoviecompanion.util.Constants;
import com.mhamed.mymoviecompanion.util.SimpleCallback;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = "MOVIE_DETAIL_ACTIVITY";
    private final MovieService movieService = ApiClient.getInstance();

    private ActivityMovieDetailBinding binding;

    private RecyclerView castRecyclerView;
    private Trailer trailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        Movie movie = (Movie) getIntent().getExtras().get("movie");
        binding.setMovie(movie);

        castRecyclerView = findViewById(R.id.rv_cast);

        FloatingActionButton playFAB = findViewById(R.id.play_fab);
        playFAB.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));

        ImageView movieCoverImg = findViewById(R.id.detail_movie_cover);
        movieCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));

        setCastRecyclerView(movie.getId());
        setTrailer(movie.getId());

        playFAB.setOnClickListener(v -> {
            if (trailer.getKey() == null || trailer.getKey().isEmpty()) {
                Log.i(TAG, "Trailer key (url) is not set.");
                Toast.makeText(getApplicationContext(), "Trailer not available at the moment.", Toast.LENGTH_LONG).show();
                return;
            }
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailer.getKey()));
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.YOUTUBE_URL + trailer.getKey()));
            try {
                startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                startActivity(webIntent);
            }
        });
    }

    private void setCastRecyclerView(long id) {
        movieService.getCast(id).enqueue((SimpleCallback<CreditsResponse>) (call, response) -> {
            if (response.isSuccessful() && response.body() != null) {
                CreditsResponse apiResponse = response.body();
                List<Cast> castList = apiResponse.getCast();
                CastAdapter castAdapter = new CastAdapter(castList);
                castRecyclerView.setAdapter(castAdapter);
                castRecyclerView.setLayoutManager(new LinearLayoutManager(MovieDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
            } else {
                Log.e(TAG, response.errorBody().toString());
            }
        });
    }

    private void setTrailer(Long id) {
        movieService.getVideos(id).enqueue((SimpleCallback<TrailersResponse>) (call, response) -> {
            if (response.isSuccessful() && response.body() != null) {
                trailer = response.body().getTrailers()
                        .stream()
                        .filter(t -> t.getType().equals("Trailer"))
                        .filter(t -> t.getSite().equals("YouTube"))
                        .findFirst()
                        .orElse(null);
            } else {
                Log.e(TAG, response.errorBody().toString());
            }
        });
    }
}
