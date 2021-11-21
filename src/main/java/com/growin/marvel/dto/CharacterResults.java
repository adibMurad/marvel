package com.growin.marvel.dto;

import com.growin.marvel.model.Thumbnail;
import lombok.Data;

import java.util.List;

@Data
public class CharacterResults {
    private int code;
    private String status;
    private CharacterData data;

    @Data
    public static class CharacterData {
        private int offset;
        private int limit;
        private int count;
        private List<Result> results;
    }

    @Data
    public static class Result {
        private int id;
        private String name;
        private String description;
        private String modified;
        private Thumbnail thumbnail;
    }
}
