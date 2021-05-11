package com.mhamed.mymoviecompanion.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mhamed.mymoviecompanion.model.Genre;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GenreUtil {

    private static final String TMDB_GENRES_FILENAME = "tmdb_genres.json";

    private static String getGenresAsJSONStringFromAssets(Context context) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(TMDB_GENRES_FILENAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }

    public static List<Genre> getGenresFromAssets(Context context) {
        String jsonFileString = getGenresAsJSONStringFromAssets(context);
        Gson gson = new Gson();
        Type listGenreType = new TypeToken<List<Genre>>() {
        }.getType();
        return gson.fromJson(jsonFileString, listGenreType);
    }
}
