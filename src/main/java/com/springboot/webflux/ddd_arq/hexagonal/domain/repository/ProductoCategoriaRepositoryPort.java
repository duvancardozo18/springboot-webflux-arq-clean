package com.springboot.webflux.ddd_arq.hexagonal.domain.repository;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.ProductoCategoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoCategoriaRepositoryPort {
    Flux<ProductoCategoria> findAll();

    Mono<ProductoCategoria> findById(String id);

    Mono<ProductoCategoria> save(ProductoCategoria pc);

    Flux<ProductoCategoria> findByProductoId(ProductoId productoId);

    Flux<ProductoCategoria> findByCategoriaId(CategoriaId categoriaId);

    Mono<Void> deleteById(String id);

}