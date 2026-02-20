package com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects;

import java.util.Objects;

public record Precio(Double value) {
    public Precio {
        Objects.requireNonNull(value, "Precio no puede ser null");
        if (value < 0) {
            throw new IllegalArgumentException("Precio no puede ser negativo");
        }
    }
}
