package com.mercadolibre.desafiospring.exceptions;

import com.mercadolibre.desafiospring.exceptions.user.UserException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> objectNotFound(DataIntegrityViolationException e) {
        StandardError err = new StandardError(HttpStatus.CONFLICT.value(),
                "Duplicate entry", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<StandardError> userNotFound(UserException e){
        StandardError err = new StandardError(e.getStatus(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.valueOf(e.getStatus())).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e) {
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),
                "Validation error", System.currentTimeMillis());
        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<StandardError> invalidDate(DateTimeParseException e){
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),
                "Invalid Date format. Dates must be passed in the format YYYY-MM-DD", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}