package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.exception;

import com.springboot.webflux.ddd_arq.hexagonal.domain.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 404);
        body.put("error", ex.getMessage());
        return ResponseEntity.status(404).body(body);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<?> handleValidation(WebExchangeBindException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("errors", ex.getFieldErrors());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<?> handleBadRequest(ServerWebInputException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("error", ex.getReason());
        return ResponseEntity.badRequest().body(body);
    }
}