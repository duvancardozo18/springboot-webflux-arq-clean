package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.producto;

import com.springboot.webflux.ddd_arq.hexagonal.domain.exceptions.NotFoundException;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import reactor.core.publisher.Mono;

public class EliminarProductoUseCase {

    private final ProductoRepositoryPort repository;

    public EliminarProductoUseCase(ProductoRepositoryPort repository) {
        this.repository = repository;
    }

    public Mono<Void> execute(ProductoId id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Producto no encontrado")))
                .flatMap(p -> repository.deleteById(id));
    }
}
