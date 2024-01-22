package com.example.mercadinho.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.mercadinho.model.Error.ErrorMensage;
import com.example.mercadinho.model.Exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?>handlerResourceNotFoundException(ResourceNotFoundException ex){
        ErrorMensage error = new ErrorMensage("NOTFOUND",ex.getMessage(), 404);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}