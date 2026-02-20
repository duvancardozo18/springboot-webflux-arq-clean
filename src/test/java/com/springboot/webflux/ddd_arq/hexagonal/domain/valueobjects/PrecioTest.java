package com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Precio Value Object Tests")
class PrecioTest {

    @Test
    @DisplayName("Debe crear un Precio válido")
    void debeCrearPrecioValido() {
        // Given
        Double valorPrecio = 99.99;

        // When
        Precio precio = new Precio(valorPrecio);

        // Then
        assertNotNull(precio);
        assertEquals(valorPrecio, precio.value());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el precio es null")
    void debeLanzarExcepcionCuandoPrecioEsNull() {
        // When & Then
        assertThrows(NullPointerException.class, () -> new Precio(null));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el precio es negativo")
    void debeLanzarExcepcionCuandoPrecioEsNegativo() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new Precio(-1.0));
    }

    @Test
    @DisplayName("Debe aceptar precio cero")
    void debeAceptarPrecioCero() {
        // Given
        Double precioCero = 0.0;

        // When
        Precio precio = new Precio(precioCero);

        // Then
        assertNotNull(precio);
        assertEquals(0.0, precio.value());
    }

    @Test
    @DisplayName("Debe aceptar precios decimales")
    void debeAceptarPreciosDecimales() {
        // Given
        Double precioDecimal = 19.99;

        // When
        Precio precio = new Precio(precioDecimal);

        // Then
        assertNotNull(precio);
        assertEquals(precioDecimal, precio.value());
    }

    @Test
    @DisplayName("Debe aceptar precios muy grandes")
    void debeAceptarPreciosMuyGrandes() {
        // Given
        Double precioGrande = 999999.99;

        // When
        Precio precio = new Precio(precioGrande);

        // Then
        assertNotNull(precio);
        assertEquals(precioGrande, precio.value());
    }
}
