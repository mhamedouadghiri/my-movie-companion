package com.mhamed.mymoviecompanion.Entity;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.io.Serializable;

@Entity(foreignKeys = {@ForeignKey(entity = User.class,
        parentColumns = "id_user",
        childColumns = "id_user"
)
},primaryKeys = {"id_film","id_user"})

public class FilmsVus implements Serializable {
    @NonNull
    private int id_user;
    @NonNull
    private String id_film;
    private float note;
    private String critique;

    public FilmsVus(int id_user, String id_film, float note, String critique) {
        this.id_user = id_user;
        this.id_film = id_film;
        this.note = note;
        this.critique = critique;
    }

    public int getId_user() {
        return id_user;
    }

    public String getId_film() {
        return id_film;
    }

    public float getNote() {
        return note;
    }

    public String getCritique() {
        return critique;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_film(String id_film) {
        this.id_film = id_film;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public void setCritique(String critique) {
        this.critique = critique;
    }
}
