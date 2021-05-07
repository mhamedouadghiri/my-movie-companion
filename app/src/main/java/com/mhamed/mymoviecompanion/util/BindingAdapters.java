package com.mhamed.mymoviecompanion.util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.mhamed.mymoviecompanion.model.ImageSizeType;

public class BindingAdapters {

    @BindingAdapter({"imagePath", "imageSizeType"})
    public static void loadImage(ImageView imageView, String imagePath, ImageSizeType imageSizeType) {
        String baseImageUrl;
        switch (imageSizeType) {
            case BACKDROP:
                baseImageUrl = Constants.BASE_BACKDROP_IMAGE_URL;
                break;
            case POSTER:
                baseImageUrl = Constants.BASE_POSTER_IMAGE_URL;
                break;
            case PROFILE:
                baseImageUrl = Constants.BASE_PROFILE_IMAGE_URL;
                break;
            default:
                baseImageUrl = Constants.BASE_ORIGINAL_IMAGE_URL;
                break;
        }
        Glide.with(imageView.getContext()).load(baseImageUrl + imagePath).into(imageView);
    }
}
