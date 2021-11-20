package com.growin.marvel.model;

import lombok.Data;

@Data
public class MarvelCharacter {
    private int id;
    private String name;
    private String description;
    private Thumbnail thumbnail;
}
