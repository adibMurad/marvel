package com.growin.marvel.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class TranslationFeignExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public ErrorResponse handleException(TranslationFeignException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorResponse(exception.getHttpStatus(), Integer.toString(exception.getCode()), exception.getMessage());
    }
}
