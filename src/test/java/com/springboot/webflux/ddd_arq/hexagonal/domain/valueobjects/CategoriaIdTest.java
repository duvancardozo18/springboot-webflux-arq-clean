package com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CategoriaId Value Object Tests")
class CategoriaIdTest {

    @Test
    @DisplayName("Debe crear un CategoriaId válido")
    void debeCrearCategoriaIdValido() {
        // Given
        String valorId = "cat-456";

        // When
        CategoriaId categoriaId = new CategoriaId(valorId);

        // Then
        assertNotNull(categoriaId);
        assertEquals(valorId, categoriaId.value());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el CategoriaId es null")
    void debeLanzarExcepcionCuandoCategoriaIdEsNull() {
        // When & Then
        assertThrows(NullPointerException.class, () -> new CategoriaId(null));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el CategoriaId está vacío")
    void debeLanzarExcepcionCuandoCategoriaIdEstaVacio() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new CategoriaId(""));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el CategoriaId solo contiene espacios")
    void debeLanzarExcepcionCuandoCategoriaIdSoloContieneEspacios() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new CategoriaId("   "));
    }
}
