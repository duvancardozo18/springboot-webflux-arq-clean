package com.springboot.webflux.ddd_arq.hexagonal.domain.entities;

import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProductoCategoria Entity Tests")
class ProductoCategoriaTest {

    @Test
    @DisplayName("Debe crear un ProductoCategoria válido con todos los campos")
    void debeCrearProductoCategoriaValidoConTodosLosCampos() {
        // Given
        String id = "pc-1";
        ProductoId productoId = new ProductoId("prod-1");
        CategoriaId categoriaId = new CategoriaId("cat-1");

        // When
        ProductoCategoria productoCategoria = new ProductoCategoria(id, productoId, categoriaId);

        // Then
        assertNotNull(productoCategoria);
        assertEquals(id, productoCategoria.id());
        assertEquals(productoId, productoCategoria.productoId());
        assertEquals(categoriaId, productoCategoria.categoriaId());
    }

    @Test
    @DisplayName("Debe crear un ProductoCategoria con id null")
    void debeCrearProductoCategoriaConIdNull() {
        // Given
        ProductoId productoId = new ProductoId("prod-1");
        CategoriaId categoriaId = new CategoriaId("cat-1");

        // When
        ProductoCategoria productoCategoria = new ProductoCategoria(null, productoId, categoriaId);

        // Then
        assertNotNull(productoCategoria);
        assertNull(productoCategoria.id());
        assertEquals(productoId, productoCategoria.productoId());
        assertEquals(categoriaId, productoCategoria.categoriaId());
    }

    @Test
    @DisplayName("Dos ProductoCategoria con los mismos valores deben ser iguales")
    void dosProductoCategoriasConMismosValoresDebenSerIguales() {
        // Given
        String id = "pc-1";
        ProductoId productoId = new ProductoId("prod-1");
        CategoriaId categoriaId = new CategoriaId("cat-1");

        ProductoCategoria pc1 = new ProductoCategoria(id, productoId, categoriaId);
        ProductoCategoria pc2 = new ProductoCategoria(id, productoId, categoriaId);

        // When & Then
        assertEquals(pc1, pc2);
        assertEquals(pc1.hashCode(), pc2.hashCode());
    }
}
