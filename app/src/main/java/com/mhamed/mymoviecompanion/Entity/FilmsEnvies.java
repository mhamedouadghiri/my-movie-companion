package com.mhamed.mymoviecompanion.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.io.Serializable;

@Entity(foreignKeys = {@ForeignKey(entity = User.class,
        parentColumns = "id_user",
        childColumns = "id_fkuser"
        )
},primaryKeys = {"id_film","id_fkuser"})

public class FilmsEnvies implements Serializable {

    @NonNull
    private String id_film;
    @NonNull
    private int id_fkuser;

    public FilmsEnvies(String id_film, int id_fkuser) {
        this.id_film = id_film;
        this.id_fkuser = id_fkuser;
    }

    public String getId_film() {
        return id_film;
    }

    public int getId_fkuser() {
        return id_fkuser;
    }

    public void setId_film(String id_film) {
        this.id_film = id_film;
    }

    public void setId_fkuser(int id_fkuser) {
        this.id_fkuser = id_fkuser;
    }
}
