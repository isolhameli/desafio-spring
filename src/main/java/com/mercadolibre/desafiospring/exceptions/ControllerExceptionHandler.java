package com.mercadolibre.desafiospring.exceptions;

import com.mercadolibre.desafiospring.exceptions.user.UserException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> objectNotFound(DataIntegrityViolationException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.CONFLICT.value(), "Registro duplicado", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<StandardError> userNotFound(UserException e){
        StandardError err = new StandardError(e.getStatus(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.valueOf(e.getStatus())).body(err);
    }

}