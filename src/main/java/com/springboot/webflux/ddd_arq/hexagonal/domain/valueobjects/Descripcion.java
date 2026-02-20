package com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects;

import java.util.Objects;

public record Descripcion(String value) {
    public Descripcion {
        Objects.requireNonNull(value, "Descripcion no puede ser null");
        if (value.length() > 500) {
            throw new IllegalArgumentException("Descripcion no puede exceder 500 caracteres");
        }
    }
}
