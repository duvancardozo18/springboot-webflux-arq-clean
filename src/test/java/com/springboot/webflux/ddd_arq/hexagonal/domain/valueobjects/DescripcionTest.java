package com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Descripcion Value Object Tests")
class DescripcionTest {

    @Test
    @DisplayName("Debe crear una Descripcion válida")
    void debeCrearDescripcionValida() {
        // Given
        String valorDescripcion = "Esta es una descripción de prueba";

        // When
        Descripcion descripcion = new Descripcion(valorDescripcion);

        // Then
        assertNotNull(descripcion);
        assertEquals(valorDescripcion, descripcion.value());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando la descripción es null")
    void debeLanzarExcepcionCuandoDescripcionEsNull() {
        // When & Then
        assertThrows(NullPointerException.class, () -> new Descripcion(null));
    }

    @Test
    @DisplayName("Debe aceptar descripción vacía")
    void debeAceptarDescripcionVacia() {
        // Given
        String descripcionVacia = "";

        // When
        Descripcion descripcion = new Descripcion(descripcionVacia);

        // Then
        assertNotNull(descripcion);
        assertEquals("", descripcion.value());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando la descripción excede 500 caracteres")
    void debeLanzarExcepcionCuandoDescripcionExcede500Caracteres() {
        // Given
        String descripcionLarga = "a".repeat(501);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new Descripcion(descripcionLarga));
    }

    @Test
    @DisplayName("Debe aceptar una descripción de exactamente 500 caracteres")
    void debeAceptarDescripcionDe500Caracteres() {
        // Given
        String descripcionLimite = "a".repeat(500);

        // When
        Descripcion descripcion = new Descripcion(descripcionLimite);

        // Then
        assertNotNull(descripcion);
        assertEquals(500, descripcion.value().length());
    }
}
