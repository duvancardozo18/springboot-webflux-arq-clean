package com.springboot.webflux.ddd_arq.hexagonal.domain.entities;

import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;

public record ProductoCategoria(
                String id,
                ProductoId productoId,
                CategoriaId categoriaId) {
}
