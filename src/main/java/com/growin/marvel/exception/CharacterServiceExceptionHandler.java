package com.growin.marvel.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CharacterServiceExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public ErrorResponse handleException(CharacterServiceException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorResponse(exception.getHttpStatus(), null, exception.getMessage());
    }
}
