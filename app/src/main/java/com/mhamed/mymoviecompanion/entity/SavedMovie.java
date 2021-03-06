package com.mhamed.mymoviecompanion.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.io.Serializable;

@Entity(foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")},
        primaryKeys = {"movieId", "userId"})
public class SavedMovie extends BaseMovieEntity implements Serializable {

    @NonNull
    private Boolean saved;

    public SavedMovie(@NonNull Long userId, @NonNull String movieId, @NonNull Boolean saved, @NonNull String title, @NonNull String posterPath) {
        super(userId, movieId, title, posterPath);
        this.saved = saved;
    }

    @NonNull
    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(@NonNull Boolean saved) {
        this.saved = saved;
    }
}
