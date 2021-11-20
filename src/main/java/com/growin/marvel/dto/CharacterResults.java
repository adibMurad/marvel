package com.growin.marvel.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CharacterResults {
    private int code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private String etag;
    private CharacterData data;

    @Data
    public static class Thumbnail {
        private String path;
        private String extension;
    }

    @Data
    public static class Result {
        private int id;
        private String name;
        private String description;
        private Date modified;
        private Thumbnail thumbnail;
    }

    @Data
    public static class CharacterData {
        private int offset;
        private int limit;
        private int total;
        private int count;
        private List<Result> results;
    }
}
