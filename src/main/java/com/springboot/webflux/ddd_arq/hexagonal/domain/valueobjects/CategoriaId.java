package com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects;

import java.util.Objects;

public record CategoriaId(String value) {
    public CategoriaId {
        Objects.requireNonNull(value, "CategoriaId no puede ser null");
        if (value.isBlank()) {
            throw new IllegalArgumentException("CategoriaId no puede estar vacío");
        }
    }
}
