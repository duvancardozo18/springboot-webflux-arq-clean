package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.mapper;

import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.CategoriaDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.CategoriaResponseDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.ProductoDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.ProductoResponseDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.ProductoCategoriaResponseDTO;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Categoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Producto;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.ProductoCategoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.*;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    // Categoria - Request DTO to Domain
    public Categoria toDomain(CategoriaDTO dto) {
        return new Categoria(
                null,
                new Nombre(dto.getNombre()),
                new Descripcion(dto.getDescripcion()));
    }

    // Categoria - Request DTO to Domain with ID
    public Categoria toDomain(String id, CategoriaDTO dto) {
        return new Categoria(
                new CategoriaId(id),
                new Nombre(dto.getNombre()),
                new Descripcion(dto.getDescripcion()));
    }

    // Categoria - Domain to Response DTO
    public CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.id() != null ? categoria.id().value() : null,
                categoria.nombre().value(),
                categoria.descripcion().value());
    }

    // Producto - Request DTO to Domain
    public Producto toDomain(ProductoDTO dto) {
        return new Producto(
                null,
                new Nombre(dto.getNombre()),
                new Precio(dto.getPrecio()));
    }

    // Producto - Request DTO to Domain with ID
    public Producto toDomain(String id, ProductoDTO dto) {
        return new Producto(
                new ProductoId(id),
                new Nombre(dto.getNombre()),
                new Precio(dto.getPrecio()));
    }

    // Producto - Domain to Response DTO
    public ProductoResponseDTO toResponseDTO(Producto producto) {
        return new ProductoResponseDTO(
                producto.id() != null ? producto.id().value() : null,
                producto.nombre().value(),
                producto.precio().value());
    }

    // ProductoCategoria - Domain to Response DTO
    public ProductoCategoriaResponseDTO toResponseDTO(ProductoCategoria pc) {
        return new ProductoCategoriaResponseDTO(
                pc.id(),
                pc.productoId().value(),
                pc.categoriaId().value());
    }

    // Helper methods to create Value Objects from Strings
    public ProductoId toProductoId(String id) {
        return new ProductoId(id);
    }

    public CategoriaId toCategoriaId(String id) {
        return new CategoriaId(id);
    }

    public Nombre toNombre(String nombre) {
        return new Nombre(nombre);
    }
}
