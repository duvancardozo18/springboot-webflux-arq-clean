    package com.springboot.webflux.ddd_arq.hexagonal.domain.exceptions;

    public class NotFoundException extends RuntimeException {

        public NotFoundException(String message) {
            super(message);
        }
    }