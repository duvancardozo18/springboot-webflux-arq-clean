package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.categoria;

import com.springboot.webflux.ddd_arq.hexagonal.domain.exceptions.NotFoundException;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.CategoriaRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
import reactor.core.publisher.Mono;

public class EliminarCategoriaUseCase {

    private final CategoriaRepositoryPort repository;

    public EliminarCategoriaUseCase(CategoriaRepositoryPort repository) {
        this.repository = repository;
    }

    public Mono<Void> execute(CategoriaId id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Categoría no encontrada")))
                .flatMap(cat -> repository.deleteById(id));
    }
}
