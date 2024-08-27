package com.carrefour.kata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerGlobal {
    @ExceptionHandler(CustomException.class)
    public Mono<ResponseEntity<String>> handleCustomException(Exception ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Mono<ResponseEntity<String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        List<String> details = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            details.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(details.toString()));
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public Mono<ResponseEntity<String>> handleGeneralException(RuntimeException ex) {
        ex.printStackTrace();
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An internal fault has occurred !"));

    }
}