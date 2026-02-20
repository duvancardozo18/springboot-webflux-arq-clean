package com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProductoId Value Object Tests")
class ProductoIdTest {

    @Test
    @DisplayName("Debe crear un ProductoId válido")
    void debeCrearProductoIdValido() {
        // Given
        String valorId = "prod-123";

        // When
        ProductoId productoId = new ProductoId(valorId);

        // Then
        assertNotNull(productoId);
        assertEquals(valorId, productoId.value());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el ProductoId es null")
    void debeLanzarExcepcionCuandoProductoIdEsNull() {
        // When & Then
        assertThrows(NullPointerException.class, () -> new ProductoId(null));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el ProductoId está vacío")
    void debeLanzarExcepcionCuandoProductoIdEstaVacio() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new ProductoId(""));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el ProductoId solo contiene espacios")
    void debeLanzarExcepcionCuandoProductoIdSoloContieneEspacios() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new ProductoId("   "));
    }
}
