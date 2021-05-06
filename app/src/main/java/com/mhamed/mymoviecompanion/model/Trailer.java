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

    @SerializedName("type")
    private String type;

    public Trailer() {
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

    public void setType(String type) {
        this.type = type;
    }
}
