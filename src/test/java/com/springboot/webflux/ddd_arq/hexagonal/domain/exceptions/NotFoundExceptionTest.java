package com.springboot.webflux.ddd_arq.hexagonal.domain.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("NotFoundException Tests")
class NotFoundExceptionTest {

    @Test
    @DisplayName("Debe crear NotFoundException con mensaje")
    void debeCrearNotFoundExceptionConMensaje() {
        // Given
        String mensaje = "Recurso no encontrado";

        // When
        NotFoundException exception = new NotFoundException(mensaje);

        // Then
        assertNotNull(exception);
        assertEquals(mensaje, exception.getMessage());
    }

    @Test
    @DisplayName("NotFoundException debe ser una RuntimeException")
    void notFoundExceptionDebeSerRuntimeException() {
        // Given
        NotFoundException exception = new NotFoundException("Error");

        // Then
        assertTrue(exception instanceof RuntimeException);
    }
}
