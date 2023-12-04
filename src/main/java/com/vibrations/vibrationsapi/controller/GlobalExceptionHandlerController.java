package com.vibrations.vibrationsapi.controller;

import com.vibrations.vibrationsapi.exception.ValidationException;
import com.vibrations.vibrationsapi.exception.model.Error;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * Controller for handling Errors in general
 * Used primarily for Validation Exceptions
 */
@ControllerAdvice
public class GlobalExceptionHandlerController {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> validation(ValidationException ex, HttpServletRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setTimestamp(new Date().getTime());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, null, HttpStatus.BAD_REQUEST);
    }
}
