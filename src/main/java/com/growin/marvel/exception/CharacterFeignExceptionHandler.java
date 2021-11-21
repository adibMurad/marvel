package com.growin.marvel.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CharacterFeignExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public ErrorResponse handleException(CharacterFeignException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorResponse(exception.getHttpStatus(), exception.getCode(), exception.getMessage());
    }
}
