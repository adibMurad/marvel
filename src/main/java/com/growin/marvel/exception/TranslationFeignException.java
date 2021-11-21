package com.growin.marvel.exception;

import lombok.Getter;

@Getter
public class TranslationFeignException extends RuntimeException {
    private static final long serialVersionUID = 8008228102847628393L;
    private final int httpStatus;
    private final int code;

    public TranslationFeignException(int httpStatus, int code, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
    }
}
