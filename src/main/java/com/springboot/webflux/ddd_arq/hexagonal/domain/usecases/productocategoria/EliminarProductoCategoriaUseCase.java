package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.productocategoria;

import com.springboot.webflux.ddd_arq.hexagonal.domain.exceptions.NotFoundException;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoCategoriaRepositoryPort;
import reactor.core.publisher.Mono;

public class EliminarProductoCategoriaUseCase {

    private final ProductoCategoriaRepositoryPort repository;

    public EliminarProductoCategoriaUseCase(ProductoCategoriaRepositoryPort repository) {
        this.repository = repository;
    }

    public Mono<Void> execute(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Relación producto-categoría no encontrada")))
                .flatMap(rel -> repository.deleteById(id));
    }
}
