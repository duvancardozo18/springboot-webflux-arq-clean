package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mapper;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Categoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Producto;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.ProductoCategoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.*;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo.CategoriaMongoEntity;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo.ProductoCategoriaMongoEntity;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo.ProductoMongoEntity;
import org.springframework.stereotype.Component;

@Component
public class DomainMapper {

    // Producto mapping
    public Producto toDomain(ProductoMongoEntity e) {
        return new Producto(
                e.getId() != null ? new ProductoId(e.getId()) : null,
                new Nombre(e.getNombre()),
                new Precio(e.getPrecio()));
    }

    public ProductoMongoEntity toEntity(Producto p) {
        ProductoMongoEntity e = new ProductoMongoEntity();
        e.setId(p.id() != null ? p.id().value() : null);
        e.setNombre(p.nombre().value());
        e.setPrecio(p.precio().value());
        return e;
    }

    // Categoria mapping
    public Categoria toDomain(CategoriaMongoEntity entity) {
        if (entity == null)
            return null;
        return new Categoria(
                entity.getId() != null ? new CategoriaId(entity.getId()) : null,
                new Nombre(entity.getNombre()),
                new Descripcion(entity.getDescripcion()));
    }

    public CategoriaMongoEntity toEntity(Categoria domain) {
        if (domain == null)
            return null;
        return CategoriaMongoEntity.builder()
                .id(domain.id() != null ? domain.id().value() : null)
                .nombre(domain.nombre().value())
                .descripcion(domain.descripcion().value())
                .build();
    }

    // ProductoCategoria mapping
    public ProductoCategoria toDomain(ProductoCategoriaMongoEntity entity) {
        if (entity == null)
            return null;
        return new ProductoCategoria(
                entity.getId(),
                new ProductoId(entity.getProductoId()),
                new CategoriaId(entity.getCategoriaId()));
    }

    public ProductoCategoriaMongoEntity toEntity(ProductoCategoria domain) {
        if (domain == null)
            return null;
        return ProductoCategoriaMongoEntity.builder()
                .id(domain.id())
                .productoId(domain.productoId().value())
                .categoriaId(domain.categoriaId().value())
                .build();
    }
}