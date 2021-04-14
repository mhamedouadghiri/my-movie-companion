package com.mhamed.mymoviecompanion.recommender;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mhamed.mymoviecompanion.recommender.data.FileUtil;
import com.mhamed.mymoviecompanion.recommender.data.MovieItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestRecSys extends AppCompatActivity {

    private static final String TAG = "TestRecSys";
    private static final String CONFIG_PATH = "config.json";

    private Config config;
    private RecommendationClient client;
    private final List<MovieItem> allMovies = new ArrayList<>();
    private final List<MovieItem> selectedMovies = new ArrayList<>();
    private final Map<Integer, MovieItem> movieItemMap = new HashMap<>();

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            config = FileUtil.loadConfig(getAssets(), CONFIG_PATH);
        } catch (
                IOException ex) {
            Log.e(TAG, String.format("Error occurs when loading config %s: %s.", CONFIG_PATH, ex));
        }

        try {
            allMovies.clear();
            allMovies.addAll(FileUtil.loadMovieList(getAssets(), config.movieList));
        } catch (IOException ex) {
            Log.e(TAG, String.format("Error occurs when loading movies %s: %s.", config.movieList, ex));
        }

        client = new RecommendationClient(this, config);
        handler = new Handler();

        try {
            Collection<MovieItem> collection = FileUtil.loadMovieList(getAssets(), config.movieList);
            movieItemMap.clear();
            for (MovieItem item : collection) {
                movieItemMap.put(item.id, item);
            }
        } catch (IOException ignored) {
        }

//        findViewById(R.id.testRecommend).setOnClickListener(v -> {
//            List<MovieItem> selected = Arrays.asList(
//                    movieItemMap.get(52722),
//                    movieItemMap.get(60040),
//                    movieItemMap.get(4552)
//                    movieItemMap.get(356)
//            );
//            recommend(selected);
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");

        List<MovieItem> favoriteMovies = allMovies.stream().limit(config.favoriteListSize).collect(Collectors.toList());

        int fm_count = 0;
        for (MovieItem favoriteMovie : favoriteMovies) {
            Log.i(TAG + "_FAVORITE_MOVIE", favoriteMovie.toString());
            fm_count++;
        }
        Log.i(TAG + "_FAVORITE_MOVIE_COUNT", Integer.toString(fm_count));

        handler.post(() -> {
            client.load();
        });

        setAndRecommend();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
        handler.post(() -> {
            client.unload();
        });
    }

    public void setAndRecommend() {
        List<MovieItem> selected = Arrays.asList(
                movieItemMap.get(52722),
                movieItemMap.get(60040),
                movieItemMap.get(4552)
//                movieItemMap.get(356)
        );
        recommend(selected);
    }

    private void recommend(final List<MovieItem> movies) {
        handler.post(() -> {
            Log.d(TAG, "Run inference with TFLite model.");
            List<RecommendationClient.Result> recommendations = client.recommend(movies);
            showResult(recommendations);
        });
    }

    private void showResult(final List<RecommendationClient.Result> recommendations) {
        for (RecommendationClient.Result recommendation : recommendations) {
            Log.i(TAG + "_RECOMMENDATION", recommendation.toString());
        }
    }
}
