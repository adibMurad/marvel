package com.growin.marvel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class TranslationRequest {
    @JsonProperty("Text")
    String text;
}
