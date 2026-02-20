package com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Nombre Value Object Tests")
class NombreTest {

    @Test
    @DisplayName("Debe crear un Nombre válido")
    void debeCrearNombreValido() {
        // Given
        String valorNombre = "Producto Test";

        // When
        Nombre nombre = new Nombre(valorNombre);

        // Then
        assertNotNull(nombre);
        assertEquals(valorNombre, nombre.value());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el nombre es null")
    void debeLanzarExcepcionCuandoNombreEsNull() {
        // When & Then
        assertThrows(NullPointerException.class, () -> new Nombre(null));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el nombre está vacío")
    void debeLanzarExcepcionCuandoNombreEstaVacio() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new Nombre(""));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el nombre solo contiene espacios")
    void debeLanzarExcepcionCuandoNombreSoloContieneEspacios() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new Nombre("   "));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el nombre excede 100 caracteres")
    void debeLanzarExcepcionCuandoNombreExcede100Caracteres() {
        // Given
        String nombreLargo = "a".repeat(101);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new Nombre(nombreLargo));
    }

    @Test
    @DisplayName("Debe aceptar un nombre de exactamente 100 caracteres")
    void debeAceptarNombreDe100Caracteres() {
        // Given
        String nombreLimite = "a".repeat(100);

        // When
        Nombre nombre = new Nombre(nombreLimite);

        // Then
        assertNotNull(nombre);
        assertEquals(100, nombre.value().length());
    }

    @Test
    @DisplayName("Debe permitir nombres con caracteres especiales")
    void debePermitirNombresConCaracteresEspeciales() {
        // Given
        String nombreConEspeciales = "Producto-123 (Especial)";

        // When
        Nombre nombre = new Nombre(nombreConEspeciales);

        // Then
        assertNotNull(nombre);
        assertEquals(nombreConEspeciales, nombre.value());
    }
}
