package com.mhamed.mymoviecompanion.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosResponse {

    @SerializedName("results")
    private List<Video> videos;

    public VideosResponse() {
    }

    public List<Video> getVideos() {
        return videos;
    }

    public Video getFirstYoutubeTrailer() {
        return videos.stream()
                .filter(video -> video.getType().equals("Trailer"))
                .filter(video -> video.getSite().equals("YouTube"))
                .findFirst()
                .orElse(null);
    }
}
