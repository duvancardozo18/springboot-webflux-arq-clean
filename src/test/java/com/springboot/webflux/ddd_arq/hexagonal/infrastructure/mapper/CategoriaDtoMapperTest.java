package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.mapper;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Categoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Producto;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.ProductoCategoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.*;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.CategoriaDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.CategoriaResponseDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.ProductoDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.ProductoResponseDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.ProductoCategoriaResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CategoriaDtoMapper Tests")
class CategoriaDtoMapperTest {

    private DtoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new DtoMapper();
    }

    @Test
    @DisplayName("Debe mapear CategoriaDTO a Categoria domain")
    void debeMapearCategoriaDtoADomain() {
        // Given
        CategoriaDTO dto = new CategoriaDTO();
        dto.setNombre("Electrónica");
        dto.setDescripcion("Productos electrónicos");

        // When
        Categoria categoria = mapper.toDomain(dto);

        // Then
        assertNotNull(categoria);
        assertNull(categoria.id());
        assertEquals("Electrónica", categoria.nombre().value());
        assertEquals("Productos electrónicos", categoria.descripcion().value());
    }

    @Test
    @DisplayName("Debe mapear Categoria domain a CategoriaResponseDTO")
    void debeMapearCategoriaDomainAResponseDto() {
        // Given
        Categoria categoria = new Categoria(
                new CategoriaId("cat-123"),
                new Nombre("Electrónica"),
                new Descripcion("Productos electrónicos"));

        // When
        CategoriaResponseDTO dto = mapper.toResponseDTO(categoria);

        // Then
        assertNotNull(dto);
        assertEquals("cat-123", dto.getId());
        assertEquals("Electrónica", dto.getNombre());
        assertEquals("Productos electrónicos", dto.getDescripcion());
    }

    @Test
    @DisplayName("Debe mapear Categoria con id null a ResponseDTO")
    void debeMapearCategoriaConIdNullAResponseDto() {
        // Given
        Categoria categoria = new Categoria(
                null,
                new Nombre("Electrónica"),
                new Descripcion("Productos electrónicos"));

        // When
        CategoriaResponseDTO dto = mapper.toResponseDTO(categoria);

        // Then
        assertNotNull(dto);
        assertNull(dto.getId());
        assertEquals("Electrónica", dto.getNombre());
        assertEquals("Productos electrónicos", dto.getDescripcion());
    }

    @Test
    @DisplayName("Debe mapear ProductoDTO a Producto domain")
    void debeMapearProductoDtoADomain() {
        // Given
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre("Laptop");
        dto.setPrecio(999.99);

        // When
        Producto producto = mapper.toDomain(dto);

        // Then
        assertNotNull(producto);
        assertNull(producto.id());
        assertEquals("Laptop", producto.nombre().value());
        assertEquals(999.99, producto.precio().value());
    }

    @Test
    @DisplayName("Debe mapear Producto domain a ProductoResponseDTO")
    void debeMapearProductoDomainAResponseDto() {
        // Given
        Producto producto = new Producto(
                new ProductoId("prod-123"),
                new Nombre("Laptop"),
                new Precio(999.99));

        // When
        ProductoResponseDTO dto = mapper.toResponseDTO(producto);

        // Then
        assertNotNull(dto);
        assertEquals("prod-123", dto.getId());
        assertEquals("Laptop", dto.getNombre());
        assertEquals(999.99, dto.getPrecio());
    }

    @Test
    @DisplayName("Debe mapear Producto con id null a ResponseDTO")
    void debeMapearProductoConIdNullAResponseDto() {
        // Given
        Producto producto = new Producto(
                null,
                new Nombre("Laptop"),
                new Precio(999.99));

        // When
        ProductoResponseDTO dto = mapper.toResponseDTO(producto);

        // Then
        assertNotNull(dto);
        assertNull(dto.getId());
        assertEquals("Laptop", dto.getNombre());
        assertEquals(999.99, dto.getPrecio());
    }

    @Test
    @DisplayName("Debe mapear ProductoCategoria domain a ProductoCategoriaResponseDTO")
    void debeMapearProductoCategoriaDomainAResponseDto() {
        // Given
        ProductoCategoria productoCategoria = new ProductoCategoria(
                "pc-123",
                new ProductoId("prod-1"),
                new CategoriaId("cat-1"));

        // When
        ProductoCategoriaResponseDTO dto = mapper.toResponseDTO(productoCategoria);

        // Then
        assertNotNull(dto);
        assertEquals("pc-123", dto.getId());
        assertEquals("prod-1", dto.getProductoId());
        assertEquals("cat-1", dto.getCategoriaId());
    }
}
