package com.naitik.journalapp.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class globalhandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchException(NoSuchElementException elementException){
        return new ResponseEntity<String>("no value is present in db",HttpStatus.NOT_FOUND);
    }
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> customValidation(MethodArgumentNotValidException Exception){
        return new ResponseEntity<String>("min 2 character is required",HttpStatus.BAD_REQUEST);
    }*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
