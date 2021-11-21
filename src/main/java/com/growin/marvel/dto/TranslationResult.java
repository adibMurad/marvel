package com.growin.marvel.dto;

import lombok.Data;

import java.util.List;

@Data
public class TranslationResult {
    private List<Translation> translations;

    @Data
    public static class Translation {
        private String text;
    }
}
