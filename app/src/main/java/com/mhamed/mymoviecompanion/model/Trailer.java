package com.mhamed.mymoviecompanion.model;

import com.google.gson.annotations.SerializedName;

public class Trailer {

    @SerializedName("id")
    private String id;

    @SerializedName("key")
    private String key;

    @SerializedName("site")
    private String site;

    @SerializedName("name")
    private String title;

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
}
