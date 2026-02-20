package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.producto;

import com.springboot.webflux.ddd_arq.hexagonal.domain.exceptions.NotFoundException;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Producto;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import reactor.core.publisher.Mono;

public class ObtenerProductoUseCase {

    private final ProductoRepositoryPort repository;

    public ObtenerProductoUseCase(ProductoRepositoryPort repository) {
        this.repository = repository;
    }

    public Mono<Producto> execute(ProductoId id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Producto no encontrado")));
    }
}
