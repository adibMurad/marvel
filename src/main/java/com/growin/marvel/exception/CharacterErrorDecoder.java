package com.growin.marvel.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.Data;

import java.io.IOException;

public class CharacterErrorDecoder implements ErrorDecoder {
    private final ObjectMapper mapper;

    public CharacterErrorDecoder() {
        mapper = new ObjectMapper();
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorDTO errorDTO;

        try {
            errorDTO = mapper.readValue(response.body().asInputStream(), ErrorDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to decode error.", e);
        }

        return new CharacterFeignException(response.status(), errorDTO.getCode(), errorDTO.getMessage());
    }

    @Data
    private static class ErrorDTO {
        private String code;
        private String message;
    }
}
