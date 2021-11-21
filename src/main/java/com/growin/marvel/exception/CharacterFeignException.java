package com.growin.marvel.exception;

import lombok.Getter;

@Getter
public class CharacterFeignException extends RuntimeException {
    private static final long serialVersionUID = 3694912101388027958L;
    private final int httpStatus;
    private final String code;

    public CharacterFeignException(int httpStatus, String code, String message) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
