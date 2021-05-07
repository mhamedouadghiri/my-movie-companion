package com.mhamed.mymoviecompanion.util;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;

public interface SimpleCallback<T> extends Callback<T> {

    @Override
    default void onFailure(Call<T> call, Throwable t) {
        Log.e("SIMPLE_CALLBACK", t.getLocalizedMessage());
    }
}
