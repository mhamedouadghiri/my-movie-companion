package com.mhamed.mymoviecompanion.model;

import com.google.gson.annotations.SerializedName;

public class Cast {

    @SerializedName("credit_id")
    private String id;

    @SerializedName("character")
    private String characterName;

    @SerializedName("gender")
    private int gender;

    @SerializedName("name")
    private String actorName;

    @SerializedName("order")
    private int order;

    @SerializedName("profile_path")
    private String profileImagePath;

    public String getId() {
        return id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public int getGender() {
        return gender;
    }

    public String getActorName() {
        return actorName;
    }

    public int getOrder() {
        return order;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }
 
    // TODO: merge these
    String name;
    int imgLink;

    public Cast() {
    }

    public Cast(String name, int imgLink) {
        this.name = name;
        this.imgLink = imgLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgLink() {
        return imgLink;
    }

    public void setImgLink(int imgLink) {
        this.imgLink = imgLink;
    }
}
