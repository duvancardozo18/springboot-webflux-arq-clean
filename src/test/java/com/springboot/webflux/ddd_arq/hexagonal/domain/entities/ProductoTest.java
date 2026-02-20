package com.springboot.webflux.ddd_arq.hexagonal.domain.entities;

import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Nombre;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Precio;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Producto Entity Tests")
class ProductoTest {

    @Test
    @DisplayName("Debe crear un Producto válido con todos los campos")
    void debeCrearProductoValidoConTodosLosCampos() {
        // Given
        ProductoId id = new ProductoId("prod-1");
        Nombre nombre = new Nombre("Laptop");
        Precio precio = new Precio(999.99);

        // When
        Producto producto = new Producto(id, nombre, precio);

        // Then
        assertNotNull(producto);
        assertEquals(id, producto.id());
        assertEquals(nombre, producto.nombre());
        assertEquals(precio, producto.precio());
    }

    @Test
    @DisplayName("Debe crear un Producto con id null")
    void debeCrearProductoConIdNull() {
        // Given
        Nombre nombre = new Nombre("Laptop");
        Precio precio = new Precio(999.99);

        // When
        Producto producto = new Producto(null, nombre, precio);

        // Then
        assertNotNull(producto);
        assertNull(producto.id());
        assertEquals(nombre, producto.nombre());
        assertEquals(precio, producto.precio());
    }

    @Test
    @DisplayName("Dos productos con los mismos valores deben ser iguales")
    void dosProductosConMismosValoresDebenSerIguales() {
        // Given
        ProductoId id = new ProductoId("prod-1");
        Nombre nombre = new Nombre("Laptop");
        Precio precio = new Precio(999.99);

        Producto producto1 = new Producto(id, nombre, precio);
        Producto producto2 = new Producto(id, nombre, precio);

        // When & Then
        assertEquals(producto1, producto2);
        assertEquals(producto1.hashCode(), producto2.hashCode());
    }

    @Test
    @DisplayName("Dos productos con diferentes valores no deben ser iguales")
    void dosProductosConDiferentesValoresNoDebenSerIguales() {
        // Given
        Producto producto1 = new Producto(
                new ProductoId("prod-1"),
                new Nombre("Laptop"),
                new Precio(999.99));

        Producto producto2 = new Producto(
                new ProductoId("prod-2"),
                new Nombre("Mouse"),
                new Precio(29.99));

        // When & Then
        assertNotEquals(producto1, producto2);
    }
}
