package com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects;

import java.util.Objects;

public record ProductoId(String value) {
    public ProductoId {
        Objects.requireNonNull(value, "ProductoId no puede ser null");
        if (value.isBlank()) {
            throw new IllegalArgumentException("ProductoId no puede estar vacío");
        }
    }
}
