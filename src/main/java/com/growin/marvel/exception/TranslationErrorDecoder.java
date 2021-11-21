package com.growin.marvel.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.Data;

import java.io.IOException;

public class TranslationErrorDecoder implements ErrorDecoder {
    private final ObjectMapper mapper;

    public TranslationErrorDecoder() {
        mapper = new ObjectMapper();
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            TranslationError error = mapper.readValue(response.body().asInputStream(), TranslationError.class);
            return new TranslationFeignException(response.status(), error.getError().getCode(), error.getError().getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Failed to decode error.", e);
        }
    }

    @Data
    private static class TranslationError {
        ErrorData error;
    }

    @Data
    private static class ErrorData {
        private int code;
        private String message;
    }

}
