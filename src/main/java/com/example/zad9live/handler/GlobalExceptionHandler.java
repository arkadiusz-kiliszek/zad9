package com.example.zad9live.handler;

import com.example.zad9live.error.ErrorResponse;
import com.example.zad9live.exception.SdaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value
            = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public @ResponseBody ErrorResponse handleException(MethodArgumentNotValidException ex) {
        return ErrorResponse
                .builder()
                .code(HttpStatus.NOT_ACCEPTABLE.value())
                .message(ex.getLocalizedMessage())
                .build();
    }

    @ExceptionHandler(value
            = SdaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleException(SdaException ex) {
        return ErrorResponse
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getLocalizedMessage())
                .build();
    }
}
