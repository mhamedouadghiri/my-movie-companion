package com.mhamed.mymoviecompanion.model;

import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("id")
    private String id;

    @SerializedName("key")
    private String key;

    @SerializedName("site")
    private String site;

    @SerializedName("name")
    private String title;

    @SerializedName("type")
    private String type;

    public Video() {
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getSite() {
        return site;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public boolean isValidYoutubeTrailer() {
        return type != null && site != null && key != null &&
                type.equals("Trailer") && site.equals("YouTube") && !key.isEmpty();
    }
}
