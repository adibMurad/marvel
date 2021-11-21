package com.growin.marvel.exception;

import lombok.Getter;

@Getter
public class CharacterServiceException extends RuntimeException {
    private static final long serialVersionUID = 7283482982154131785L;
    private final int httpStatus;

    public CharacterServiceException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
