package com.mhamed.mymoviecompanion.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.mhamed.mymoviecompanion.R;
import com.mhamed.mymoviecompanion.model.ImageSizeType;

import java.util.List;

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

    @BindingAdapter({"items"})
    public static void setItems(ChipGroup view, List<String> genreNames) {
        Context context = view.getContext();
        for (String genreName : genreNames) {
            Chip chip = new Chip(context);
            chip.setText(genreName);
            chip.setTextColor(ColorStateList.valueOf(context.getResources().getColor(R.color.colorWhite, null)));
            chip.setChipBackgroundColorResource(R.color.colorAccent);
            chip.setChipStrokeWidth(dipToPixels(context, 1));
            chip.setChipStrokeColor(ColorStateList.valueOf(context.getResources().getColor(R.color.colorPrimaryDark, null)));
            view.addView(chip);
        }
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
}
