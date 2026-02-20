package com.springboot.webflux.ddd_arq.hexagonal.domain.entities;

import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Descripcion;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Nombre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Categoria Entity Tests")
class CategoriaTest {

    @Test
    @DisplayName("Debe crear una Categoria válida con todos los campos")
    void debeCrearCategoriaValidaConTodosLosCampos() {
        // Given
        CategoriaId id = new CategoriaId("cat-1");
        Nombre nombre = new Nombre("Electrónica");
        Descripcion descripcion = new Descripcion("Productos electrónicos");

        // When
        Categoria categoria = new Categoria(id, nombre, descripcion);

        // Then
        assertNotNull(categoria);
        assertEquals(id, categoria.id());
        assertEquals(nombre, categoria.nombre());
        assertEquals(descripcion, categoria.descripcion());
    }

    @Test
    @DisplayName("Debe crear una Categoria con id null")
    void debeCrearCategoriaConIdNull() {
        // Given
        Nombre nombre = new Nombre("Electrónica");
        Descripcion descripcion = new Descripcion("Productos electrónicos");

        // When
        Categoria categoria = new Categoria(null, nombre, descripcion);

        // Then
        assertNotNull(categoria);
        assertNull(categoria.id());
        assertEquals(nombre, categoria.nombre());
        assertEquals(descripcion, categoria.descripcion());
    }

    @Test
    @DisplayName("Dos categorías con los mismos valores deben ser iguales")
    void dosCategoriasConMismosValoresDebenSerIguales() {
        // Given
        CategoriaId id = new CategoriaId("cat-1");
        Nombre nombre = new Nombre("Electrónica");
        Descripcion descripcion = new Descripcion("Productos electrónicos");

        Categoria categoria1 = new Categoria(id, nombre, descripcion);
        Categoria categoria2 = new Categoria(id, nombre, descripcion);

        // When & Then
        assertEquals(categoria1, categoria2);
        assertEquals(categoria1.hashCode(), categoria2.hashCode());
    }

    @Test
    @DisplayName("Dos categorías con diferentes valores no deben ser iguales")
    void dosCategoriasConDiferentesValoresNoDebenSerIguales() {
        // Given
        Categoria categoria1 = new Categoria(
                new CategoriaId("cat-1"),
                new Nombre("Electrónica"),
                new Descripcion("Productos electrónicos"));

        Categoria categoria2 = new Categoria(
                new CategoriaId("cat-2"),
                new Nombre("Ropa"),
                new Descripcion("Prendas de vestir"));

        // When & Then
        assertNotEquals(categoria1, categoria2);
    }
}
