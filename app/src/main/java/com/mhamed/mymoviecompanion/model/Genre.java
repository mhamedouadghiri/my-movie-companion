package com.mhamed.mymoviecompanion.model;

import java.io.Serializable;

public class Genre implements Serializable
{

    private Long id;

    private String name;

    public Genre() {
    }
  
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
