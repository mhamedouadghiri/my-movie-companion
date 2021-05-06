package com.mhamed.mymoviecompanion.ui;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

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
import com.mhamed.mymoviecompanion.remote.api.ApiClient;
import com.mhamed.mymoviecompanion.remote.api.MovieService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MovieDetailActivity extends AppCompatActivity {

    private final MovieService movieService = ApiClient.getInstance();

    private ActivityMovieDetailBinding binding;

    private RecyclerView castRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        Movie movie = (Movie) getIntent().getExtras().get("movie");
        binding.setMovie(movie);

        castRecyclerView = findViewById(R.id.rv_cast);

        // what is this for ?
        FloatingActionButton playFAB = findViewById(R.id.play_fab);
        playFAB.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        ImageView movieCoverImg = findViewById(R.id.detail_movie_cover);
        movieCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));

        setCastRecyclerView(movie.getId());
    }

    void setCastRecyclerView(long id) {
        Call<CreditsResponse> callAsync = movieService.getCast(id);

        callAsync.enqueue(new Callback<CreditsResponse>() {

            @Override
            public void onResponse(Call<CreditsResponse> call, Response<CreditsResponse> response) {
                if (response.isSuccessful()) {
                    CreditsResponse apiResponse = response.body();
                    List<Cast> mdata = apiResponse.getCast();
                    CastAdapter castAdapter = new CastAdapter(MovieDetailActivity.this, mdata);
                    castRecyclerView.setAdapter(castAdapter);
                    castRecyclerView.setLayoutManager(new LinearLayoutManager(MovieDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
                } else {
                    Timber.e("Request Error :: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CreditsResponse> call, Throwable t) {
                Timber.e("Network Error :: " + t.getLocalizedMessage());
            }
        });

    }
}
