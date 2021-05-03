package com.mhamed.mymoviecompanion.util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BindingAdapters {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(Constants.BASE_IMAGE_URL + url).into(view);
    }
}
