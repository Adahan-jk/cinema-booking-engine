package com.cinema.catalog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> notFound(NoResourceFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(404, "Ресурс не найден"));
    }
}
