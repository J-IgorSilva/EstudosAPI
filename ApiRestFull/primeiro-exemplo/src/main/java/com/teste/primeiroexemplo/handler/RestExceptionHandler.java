package com.teste.primeiroexemplo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teste.primeiroexemplo.model.Error.ErrorMensage;
import com.teste.primeiroexemplo.model.Exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?>handlerResourceNotFoundException(ResourceNotFoundException ex){
        ErrorMensage error = new ErrorMensage("NOTFOUND",ex.getMessage(), 404);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
