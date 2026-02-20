package com.springboot.webflux.ddd_arq.hexagonal.domain.repository;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Producto;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Nombre;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoRepositoryPort {
    Flux<Producto> findAll();

    Mono<Producto> findById(ProductoId id);

    Mono<Producto> save(Producto producto);

    Mono<Void> deleteById(ProductoId id);

    Flux<Producto> findByNombreContaining(Nombre term);
}