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

public class FilmsEnvies implements Serializable {

    @NonNull
    private String id_film;
    @NonNull
    private int id_user;

    public FilmsEnvies(String id_film, int id_user) {
        this.id_film = id_film;
        this.id_user = id_user;
    }

    public String getId_film() {
        return id_film;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_film(String id_film) {
        this.id_film = id_film;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
