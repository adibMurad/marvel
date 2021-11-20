package com.growin.marvel.model;

import lombok.Data;

import java.math.BigInteger;

@Data
public class MarvelCharacter {
    private BigInteger id;
    private String name;
    private String description;
    private Thumbnail thumbnail;
}
