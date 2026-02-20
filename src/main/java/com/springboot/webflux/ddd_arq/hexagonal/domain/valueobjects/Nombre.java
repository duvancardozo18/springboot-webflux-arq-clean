package com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects;

import java.util.Objects;

public record Nombre(String value) {
    public Nombre {
        Objects.requireNonNull(value, "Nombre no puede ser null");
        if (value.isBlank()) {
            throw new IllegalArgumentException("Nombre no puede estar vacío");
        }
        if (value.length() > 100) {
            throw new IllegalArgumentException("Nombre no puede exceder 100 caracteres");
        }
    }
}
