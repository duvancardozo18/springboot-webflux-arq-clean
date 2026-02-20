package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.categoria;

import com.springboot.webflux.ddd_arq.hexagonal.domain.exceptions.NotFoundException;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Categoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.CategoriaRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
import reactor.core.publisher.Mono;

public class ObtenerCategoriaUseCase {

    private final CategoriaRepositoryPort repository;

    public ObtenerCategoriaUseCase(CategoriaRepositoryPort repository) {
        this.repository = repository;
    }

    public Mono<Categoria> execute(CategoriaId id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Categoría no encontrada")));
    }
}
