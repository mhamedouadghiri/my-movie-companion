package com.mhamed.mymoviecompanion.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mhamed.mymoviecompanion.CustomDialog;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.adapters.CastAdapter;
import com.mhamed.mymoviecompanion.databinding.ActivityMovieDetailBinding;
import com.mhamed.mymoviecompanion.entity.WatchedMovie;
import com.mhamed.mymoviecompanion.model.Cast;
import com.mhamed.mymoviecompanion.model.CreditsResponse;
import com.mhamed.mymoviecompanion.model.Movie;
import com.mhamed.mymoviecompanion.model.Video;
import com.mhamed.mymoviecompanion.model.VideosResponse;
import com.mhamed.mymoviecompanion.remote.api.ApiClient;
import com.mhamed.mymoviecompanion.remote.api.MovieService;
import com.mhamed.mymoviecompanion.util.BaseActivity;
import com.mhamed.mymoviecompanion.util.Constants;
import com.mhamed.mymoviecompanion.util.GenreUtil;
import com.mhamed.mymoviecompanion.util.SimpleCallback;
import com.mhamed.mymoviecompanion.viewmodel.WatchedMoviesViewModel;

import java.util.List;
import java.util.stream.Collectors;

import static com.mhamed.mymoviecompanion.util.Constants.PREFERENCES_LOGIN_ID;

public class MovieDetailActivity extends BaseActivity implements CustomDialog.CustomDialogInterface {

    private static final String TAG = "MOVIE_DETAIL_ACTIVITY";
    private final MovieService movieService = ApiClient.getInstance();

    private ActivityMovieDetailBinding binding;

    private SharedPreferences sharedPreferences;

    private RecyclerView castRecyclerView;
    private Video video;
    private FloatingActionButton playTrailerFAB;
    private RatingBar ratingBar;

    private WatchedMoviesViewModel watchedMoviesViewModel;

    private Movie currentMovie;
    private Long currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupToolbar();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentUserId = sharedPreferences.getLong(PREFERENCES_LOGIN_ID, -1);

        currentMovie = (Movie) getIntent().getExtras().get("movie");
        currentMovie.setGenres(
                GenreUtil.getGenresFromAssets(this)
                        .stream()
                        .filter(genre -> currentMovie.getGenreIds().contains(genre.getId()))
                        .collect(Collectors.toList())
        );

        binding.setMovie(currentMovie);

        setTitle(currentMovie.getTitle());

        castRecyclerView = findViewById(R.id.cast_recycler_view);

        watchedMoviesViewModel = new ViewModelProvider(this).get(WatchedMoviesViewModel.class);
        watchedMoviesViewModel.init(this.getApplication());

        ImageView movieCoverImg = findViewById(R.id.movie_backdrop_image_view);
        movieCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));

        setCastRecyclerView(currentMovie.getId());
        setVideo(currentMovie.getId());

        playTrailerFAB = findViewById(R.id.play_fab);
        setPlayTrailerFAB();

        new SetRatingAsync(watchedMoviesViewModel, ratingBar, this).execute(currentUserId, currentMovie.getId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setPlayTrailerFAB() {
        playTrailerFAB.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        playTrailerFAB.setOnClickListener(v -> {
            if (video == null || !video.isValidYoutubeTrailer()) {
                Log.i(TAG, "Trailer key (url) is not set.");
                Toast.makeText(getApplicationContext(), "Trailer not available at the moment.", Toast.LENGTH_LONG).show();
                return;
            }
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video.getKey()));
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.YOUTUBE_URL + video.getKey()));
            try {
                startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                startActivity(webIntent);
            }
        });

        ratingBar = findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) ->
                watchedMoviesViewModel.insertWatchedMovie(new WatchedMovie(currentUserId, String.valueOf(currentMovie.getId()), rating, ""))
        );
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.menu_share_button) {
            String text;
            if (video != null && video.isValidYoutubeTrailer()) {
                text = Constants.YOUTUBE_URL + video.getKey();
            } else {
                text = "Check out this movie: " + currentMovie.getTitle();
            }

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Share this movie");
            intent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(intent, "Share using"));
        }
        return super.onOptionsItemSelected(item);
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

    private void setVideo(Long id) {
        movieService.getVideos(id).enqueue((SimpleCallback<VideosResponse>) (call, response) -> {
            if (response.isSuccessful() && response.body() != null) {
                video = response.body().getFirstYoutubeTrailer();
            } else {
                Log.e(TAG, response.errorBody().toString());
            }
        });
    }

    public void openDialog(View view) {
        CustomDialog custom_dialog = new CustomDialog();
        custom_dialog.show(getSupportFragmentManager(), "Test CustomerDialog");
    }

    @Override
    public void applyTexts(String critique, int note) {
        Log.i("Note", String.valueOf(note));
        //Insertion de FilmsVus
        //filmsVusViewModel.insertWatchedMovie(new FilmsVus(1,video.getId(),note,critique));
    }

    private static class SetRatingAsync extends AsyncTask<Long, Void, LiveData<WatchedMovie>> {
        private final LifecycleOwner owner;
        private final WatchedMoviesViewModel watchedMoviesViewModel;
        private final RatingBar ratingBar;

        public SetRatingAsync(WatchedMoviesViewModel watchedMoviesViewModel, RatingBar ratingBar, LifecycleOwner owner) {
            this.watchedMoviesViewModel = watchedMoviesViewModel;
            this.ratingBar = ratingBar;
            this.owner = owner;
        }

        @Override
        protected LiveData<WatchedMovie> doInBackground(Long... longs) {
            return watchedMoviesViewModel.getWatchedMovieByUserIdAndMovieId(longs[0], longs[1]);
        }

        @Override
        protected void onPostExecute(LiveData<WatchedMovie> watchedMoviesLiveData) {
            super.onPostExecute(watchedMoviesLiveData);
            watchedMoviesLiveData.observe(owner, watchedMovie -> {
                if (watchedMovie != null && watchedMovie.getVote() != null) {
                    ratingBar.setRating(watchedMovie.getVote());
                }
            });
        }
    }
}
